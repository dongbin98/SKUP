package com.dbsh.skup.client;

import com.dbsh.skup.api.NoticeApi;
import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class NoticeClient {
	private NoticeApi noticeApi;
	private Retrofit retrofit;

	public static String BASE_URL = "https://www.skuniv.ac.kr/";

	public NoticeClient() {
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(HtmlConverterFactory.create(BASE_URL))
				.client(getUnsafeOkHttpClient().build())
				.build();
		noticeApi = retrofit.create(NoticeApi.class);
	}

	public OkHttpClient.Builder getUnsafeOkHttpClient() {
		try {
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
			};

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return builder;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public NoticeApi getNoticeApi() { return noticeApi; }
}
