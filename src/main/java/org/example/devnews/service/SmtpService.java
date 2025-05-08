package org.example.devnews.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.example.devnews.domain.user.UserRepository;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SmtpService {
    public final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Scheduled(cron = "0 27 17 * * *")
    public void sendMail() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo("ocar1115.shin@gmail.com");
            mimeMessageHelper.setSubject("테스트 메일");

            String content = generateNewsletterHtml("");

            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email : {}", e.getMessage());
        }
    }

    public String generateNewsletterHtml(String articles){
        String content = """
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
                        <!-- 예시 아티클들 -->
                        <div class="article">
                          <a class="title" href="#링크1">📰 [기사 제목 1]</a>
                          <div class="source">출처: [발행처 1]</div>
                          <div class="summary">[요약 내용 1]</div>
                        </div>
                                        
                        <div class="article">
                          <a class="title" href="#링크2">📰 [기사 제목 2]</a>
                          <div class="source">출처: [발행처 2]</div>
                          <div class="summary">[요약 내용 2]</div>
                        </div>
                                        
                        <div class="article">
                          <a class="title" href="#링크3">📰 [기사 제목 3]</a>
                          <div class="source">출처: [발행처 3]</div>
                          <div class="summary">[요약 내용 3]</div>
                        </div>
                                        
                        <div class="article">
                          <a class="title" href="#링크4">📰 [기사 제목 4]</a>
                          <div class="source">출처: [발행처 4]</div>
                          <div class="summary">[요약 내용 4]</div>
                        </div>
                                        
                        <div class="article">
                          <a class="title" href="#링크5">📰 [기사 제목 5]</a>
                          <div class="source">출처: [발행처 5]</div>
                          <div class="summary">[요약 내용 5]</div>
                        </div>
                                        
                        <div class="footer">
                          이 메일은 Google SMTP를 통해 자동 발송되었습니다.<br>
                          원치 않으시면 설정에서 수신을 해제해 주세요.
                        </div>
                      </div>
                    </body>
                    </html>
                                        
                    """;
        return content;
    }


}
