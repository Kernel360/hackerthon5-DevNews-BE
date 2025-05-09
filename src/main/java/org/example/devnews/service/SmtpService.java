package org.example.devnews.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.devnews.domain.article.ArticleRepository;
import org.example.devnews.domain.interestcategory.InterestCategory;
import org.example.devnews.domain.interestcategory.InterestCategoryRepository;
import org.example.devnews.domain.interestcompany.InterestCompany;
import org.example.devnews.domain.interestcompany.InterestCompanyRepository;
import org.example.devnews.domain.user.User;
import org.example.devnews.dto.article.MailDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.example.devnews.domain.user.UserRepository;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SmtpService {
    public final UserRepository userRepository;
    public final ArticleRepository articleRepository;
    public final InterestCompanyRepository interestCompanyRepository;
    public final InterestCategoryRepository interestCategoryRepository;
    private final JavaMailSender javaMailSender;

    @Scheduled(cron = "0 25 10 * * *")
    public void foundReceiver() {
        LocalDateTime today = LocalDateTime.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        for (User user: userRepository.findAll()) {
            List<Long> interestCompanies = interestCompanyRepository.findCompanyIdByUserId(user.getId());
            List<Long> interestCategories = interestCategoryRepository.findCategoryIdByUserId(user.getId());

            sendMail(user, interestCompanies, interestCategories, month, day);
        }
    }

    public void sendMail(User user, List<Long> interestCompanies, List<Long> interestCategories, int month, int day) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(String.format("%d월 %d일 DevNews 뉴스레터", month, day));

            List<MailDto> categoryArticles = articleRepository.findByCategoryId(interestCategories, PageRequest.of(0, 2));
            List<MailDto> companyArticles = articleRepository.findByCompanyId(interestCompanies, PageRequest.of(0, 2));
            String content = generateNewsletterHtml(user.getUsername(), categoryArticles, companyArticles);

            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email : {}", e.getMessage());
        }
    }

    public String generateNewsletterHtml(String userName, List<MailDto> categoryArticles, List<MailDto> companyArticles) {
        StringBuilder html = new StringBuilder();

        html.append("""
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                  <meta charset="UTF-8">
                  <title>오늘의 뉴스레터</title>
                  <link href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/variable/pretendardvariable.css" rel="stylesheet" />
                  <style>
                    body {
                      font-family: 'Pretendard', 'Helvetica Neue', Helvetica, Arial, sans-serif;
                      background-color: #f4f4f4;
                      padding: 30px 0;
                      margin: 0;
                    }
                    .container {
                      max-width: 600px;
                      margin: auto;
                      background-color: #ffffff;
                      padding: 30px;
                      border-radius: 10px;
                      box-shadow: 0 0 10px rgba(0,0,0,0.05);
                    }
                    h1 {
                      font-size: 24px;
                      color: #333333;
                      text-align: center;
                      margin-bottom: 30px;
                    }
                    .article {
                      margin-bottom: 30px;
                      border-bottom: 1px solid #e0e0e0;
                      padding-bottom: 20px;
                    }
                    .article:last-child {
                      border-bottom: none;
                    }
                    .title {
                      font-size: 18px;
                      font-weight: bold;
                      color: #333333;
                      margin-bottom: 15px;
                      text-decoration: none;
                    }
                    .source {
                      font-size: 14px;
                      color: #888888;
                      margin-bottom: 10px;
                    }
                    .summary {
                      font-size: 15px;
                      color: #444444;
                      line-height: 1.6;
                    }
                    .footer {
                      font-size: 13px;
                      color: #999999;
                      text-align: center;
                      margin-top: 40px;
                    }
                  </style>
                </head>
                <body>
                  <div class="container">
                    <h1>💌 <span style="color: #326BEA;">Dev</span>News, 오늘의 뉴스레터</h1>
                """);

        for (MailDto article : categoryArticles) {
            html.append(String.format("""
                            <div class="article">
                              <a class="title" href="%s">📰 %s</a>
                              <div class="source">%s</div>
                              <div class="summary">%s</div>
                            </div>
                    """, article.getUrl(), article.getTitle(), article.getCompanyName(), article.getSummary()));
        }

        html.append(String.format("""
            <h1 style="font-size: 24px; color: #333333; margin: 40px 0 20px 0; text-align: center;">
                💼 %s 님이 관심있는 기업 소식을 가져왔어요
            </h1>
            """, userName));

        for (MailDto article : companyArticles) {
            html.append(String.format(
                    """
                            <div class="article">
                              <a class="title" href="%s">📰 %s</a>
                              <div class="source">%s</div>
                              <div class="summary">%s</div>
                            </div>
                    """, article.getUrl(), article.getTitle(), article.getCompanyName(), article.getSummary()));
        }

        html.append("""
                    <div class="footer">
                      이 메일은 Google SMTP를 통해 자동 발송되었습니다.<br>
                      원치 않으시면 설정에서 수신을 해제해 주세요.
                    </div>
                  </div>
                </body>
                </html>
                """);

        return html.toString();
    }
}
