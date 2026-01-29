package com.example.demo.test;
public class PairObject {
	private String first;
	private String second;
	
	public PairObject() {
		
	}
	
	public PairObject(String first, String second) {
		super();
		this.first = first;
		this.second = second;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}

	public static void main(String[] args) {
		String t  = "Tài khoản thanh toán USD";
		var s = t.substring(t.length() - 4);
		System.out.println(s);


//		String url = "https://stb-livechat.fpt.ai/v36/src/index.html?scendpoint=stb-livechat.fpt.ai%3A443&time=1764038437726&mbfptai=1&styles=%7B%0A%22headerText%22%3A%222-event%22,%0A%22avatarBot%22%3A%22https%3A%2F%2Fcard.sacombank.com.vn%2Fpartner%2Fimages%2Fewallet%2Fsacombank%2Ficon-new%2FSamiicon.PNG%22,%0A%22customerLogo%22%3A%22img%2Fbig_logo.svg%22,%0A%22customerWelcomeText%22%3A%22Vui%20l%C3%B2ng%20nh%E1%BA%ADp%20t%C3%AAn%20c%E1%BB%A7a%20b%E1%BA%A1n%22,%0A%22customerButtonText%22%3A%22B%E1%BA%AFt%20%C4%91%E1%BA%A7u%22%0A%7D&load_history_chatlog=1&botcode={bot_code}&botname={bot_name}";
//		String newUrl = new URIBuilder(url)
//				.setParameter("botcode", "botCode")
//				.setParameter("use_client_sender_info", "response.isUseClientSenderInfo()")
//				.setParameter("sender_id", "senderId")
//				.setParameter("sendername", "session.getCustomerName()")
//				.setParameter("sender_token", "rsm.getResponse().getToken()")
//				.setParameter("disable_upload_file", "response.isDisableUploadFile()")
//				.build()
//				.toString();

	}
}