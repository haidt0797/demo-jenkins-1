# Giải pháp 
## Hiện trạng
Danh sách Threads tác động
1. ThreadName==FX - Remind cash receive
- Nghiệp vụ mua bán ngoại tệ
- Tham số tiếng việt:
  - title
  - messageTemplate


PA - Thay đổi: Bổ sung thêm Thread Parameter nội dung tiếng anh tương ứng
1. ThreadName==FX - Remind cash receive
- b/s tham số tiếng anh:
  - titleEn
  - messageTemplateEn
2. ThreadName==Apple Pay - Send notification to user
- b/s tham số tiếng anh:
  - titleEn
  - messageTemplateEn
  - shortContentEn

3. ThreadName==Apple Pay - Send notification to user 7 day
- b/s tham số tiếng anh:
  - titleEn
  - messageTemplateEn
  - shortContentEn


## Chi tiết

### 1. ThreadName==FX - Remind cash receive
B/s Parameter: titleEn, messageTemplateEn
```text
8100132
	ThreadName==FX - Remind cash receive
	ClassName==com.stb.schedule.thread.RemindFxTransExpireProcess
	StartupType==2
	Parameter
		startTime==08:00
		endTime==09:00
		scanDate==2
		quantityLimit==100000
		title==Mua ngoại tệ tiền mặt
		titleEn==Buy foreign currency in cash
		messageTemplate==Đề nghị mua ngoại tệ mặt <transCode> của Quý khách sắp hết hạn. Quý khách vui lòng đến <toBranchName> (<toAddress>) để nhận tiền mặt trước 17h00 ngày <expireDate> hoặc ngày làm việc liền kề trước đó nếu ngày <expireDate> là ngày thứ 7, Chủ nhật, ngày lễ hoặc ngày nghỉ khác của Ngân hàng. Vui lòng xuất trình giấy tờ cá nhân (CMND, CCCD, Passport) và đọc Mã tham chiếu để nhận tiền
		messageTemplateEn==Your request to purchase foreign currency in cash <transCode> is about to expire. Please visit the branch <toBranchName> (<toAddress>) to collect your cash before 5:00 PM on <expireDate> or the preceding business day if <expireDate> falls on a Saturday, Sunday, public holiday, or any other non-working day of the bank. Kindly present your personal identification documents (ID card, or Passport) and provide the reference code to receive your money
		LogLevel==DEBUG
		LogDir==LogDir/RemindFxTransExpireProcess
		ConnectDB==Automatic
		AlertByMail==N
		DelayTime==3600
	Status==1
	Schedule

```

### 2. ThreadName==Apple Pay - Send notification to user
B/s Parameter: titleEn, messageTemplateEn, shortContentEn
```text
8100130
	ThreadName==Apple Pay - Send notification to user
	ClassName==com.stb.schedule.thread.RemindLinkApplePayProcess
	StartupType==2
	Parameter
		startTime==08:00
		endTime==21:00
		scanDate==1
		quantityLimit==500
		title==Hoàn tất việc thêm thẻ vào APPLE PAY ngay bây giờ!
		titleEn==Finish adding your card to APPLE PAY now!
		messageTemplate==<p>Bạn chưa hoàn tất việc thêm thẻ vào Apple Pay. Chỉ với vài thao tác đơn giản, bạn sẽ được trải nghiệm phương thức thanh toán Dễ dàng, An toàn và Riêng tư mọi lúc mọi nơi tại các cửa hàng vật lý và trực tuyến có biểu tượng:</p> <p align=\"center\"><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/icon_nfc.png\" style=\"max-width:30%\" /></p> <p><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/banner_appepay3.png\" style=\"max-width:100%\" /></p> <p align=\"center\">Hướng dẫn sử dụng <a href=\"https://www.sacombank.com.vn/content/dam/sacombank/files/ca-nhan/the/dv-the/HDSD%20APPLE%20PAY.pdf\">Tại đây</a></p>
				messageTemplateEn==<p>You haven't finished adding your card to Apple Pay. With just a few simple steps, you will experience Easy, Safe and Private payment methods anytime, anywhere at physical and online stores with the icon:</p> <p align=\"center\"><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/icon_nfc.png\" style=\"max-width:30%\" /></p> <p><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/banner_appepay3.png\" style=\"max-width:100%\" /></p> <p align=\"center\">User Guide <a href=\"https://www.sacombank.com.vn/content/dam/sacombank/files/ca-nhan/the/dv-the/HDSD%20APPLE%20PAY.pdf\">Here</a></p>
		shortContent==Trải nghiệm ngay phương thức thanh toán Dễ dàng, An toàn và Riêng tư.
		shortContentEn==Experience Easy, Secure and Private payment method now.
		LogLevel==DEBUG
		LogDir==LogDir/RemindLinkApplePayProcess
		ConnectDB==Automatic
		AlertByMail==N
		DelayTime==3600
	Status==1
	Schedule

```

### 3. ThreadName==Apple Pay - Send notification to user 7 day
B/s Parameter: titleEn, messageTemplateEn, shortContentEn
```text
8100135
	ThreadName==Apple Pay - Send notification to user 7 day
	ClassName==com.stb.schedule.thread.RemindHasNotCompletedLinkApplePayWith7DaysProcess
	StartupType==2
	Parameter
		startTime==08:00
		endTime==21:00
		scanFromDate==7
		scanToDate==8
		quantityLimit==1000
		title==Hoàn tất việc thêm thẻ vào APPLE PAY ngay bây giờ!
		titleEn==Finish adding your card to APPLE PAY now!
		messageTemplate==<p>Thẻ của bạn chưa được thêm vào Ví Apple. Hoàn tất việc thêm thẻ để trải nghiệm phương thức thanh toán Dễ dàng, An toàn, Riêng tư mọi lúc mọi nơi tại các cửa hàng vật lý và trực tuyến có biểu tượng:</p> <p align=\"center\"><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/icon_nfc.png\" style=\"max-width:30%\" /></p>  <p><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/banner_appepay3.png\" style=\"max-width:100%\" /></p> <p align=\"center\">Hướng dẫn sử dụng <a href=\"https://www.sacombank.com.vn/content/dam/sacombank/files/ca-nhan/the/dv-the/HDSD%20APPLE%20PAY.pdf\">Tại đây</a></p>
		messageTemplateEn==<p>Your card has not been added to Apple Wallet. Complete adding your card to experience Easy, Safe, Private payment anytime, anywhere at physical and online stores with the icon:</p> <p align=\"center\"><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/icon_nfc.png\" style=\"max-width:30%\" /></p> <p><img alt=\"\" src=\"https://card.sacombank.com.vn/partner/images/ewallet/sacombank/icon-new/banner_appepay3.png\" style=\"max-width:100%\" /></p> <p align=\"center\">User Guide <a href=\"https://www.sacombank.com.vn/content/dam/sacombank/files/ca-nhan/the/dv-the/HDSD%20APPLE%20PAY.pdf\">Here</a></p>
		shortContent==Trải nghiệm ngay phương thức thanh toán Dễ dàng, An toàn và Riêng tư.
		shortContentEn==Experience Easy, Secure and Private payment method now.
		LogLevel==INFO
		LogDir==LogDir/RemindHasNotCompletedLinkApplePayWith7DaysProcess
		ConnectDB==Automatic
		AlertByMail==N
		DelayTime==3600
	Status==1
	Schedule

```


