package de.metafinanz.mam.backend.repository.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ACS extends AbstractCloud {
	static Logger logger = LoggerFactory.getLogger(ACS.class);

	// Innere private Klasse, die erst beim Zugriff durch die umgebende Klasse initialisiert wird
	private static final class InstanceHolder {
		// Die Initialisierung von Klassenvariablen geschieht nur einmal und wird vom ClassLoader implizit synchronisiert
		static final ACS INSTANCE = new ACS();
	}

	// Verhindere die Erzeugung des Objektes über andere Methoden
	private ACS() {	}

	// Eine nicht synchronisierte Zugriffsmethode auf Klassenebene.
	public static ACS getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	
	//============================================================================================================

	public void send() throws Exception {
		String session = createSession();

		String datum = (new Date()).toString();
		String text = "Nachricht";
		String titel = "Titel";

		sendMessage(datum, text, titel, session);
	}

	@Override
	public String createSession() throws Exception {
		URL url = null;
		URLConnection uc = null;
		String sessionId = null;
		try {
			url = new URL(URL_ACS + "users/login.json?key=" + API_KEY + "&amp;login=" + API_USR + "&amp;password=" + API_PAS + "");
			uc = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			if (conn.getResponseCode() != 200) {
				throw new Exception(conn.getResponseMessage());
			}
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			String request = sb.toString();

			if (request.contains("status\": \"ok") && request.contains("code\": 200")) {
				int number = sb.indexOf("session_id");
				String meta = sb.substring(number + 14, number + 60);
				int fin = meta.indexOf("\"");

				sessionId = meta.substring(0, fin);

				logger.info("Session Id : {}", sessionId);
			} else {
				logger.info("Kein Eintrag.");
			}
		} catch (Exception e) {
			throw new Exception("Problem beim erstellen der Session " + e);
		}
		return sessionId;
	}

	@Override
	public String sendMessage(String datum, String text, String titel, String session_id) throws Exception {
		URL url = null;
		HttpURLConnection uc = null;
		String idSession = null;
		try {

			String ruta = URL_ACS + "push_notification/notify.json?key=" + API_KEY + "";

			logger.debug(ruta);

			url = new URL(ruta);
			uc = (HttpURLConnection) url.openConnection();

			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "application/json");
			uc.setRequestProperty("Accept", "application/json");
			uc.setRequestProperty("Cookie", "_session_id=" + session_id);

			JSONObject cred = new JSONObject();
			JSONObject push = new JSONObject();
			JSONObject chan = new JSONObject();

			cred.put("alert", "Preparacion Pruebas Estres");
			cred.put("title", "Pruebas");
			cred.put("icon", "icon_notifi");
			cred.put("vibrate", true);
			cred.put("sound", "default");

			push.put("payload", cred);

			// push.put("channel","All users"); chan.put("push_notification",
			// push);

			if (logger.isDebugEnabled()) {
				logger.debug(push.toString());
				String respuestaJSON = push.toString().replace("{\"payload\":", "{channel=\"All users\",payload=");
				logger.debug(respuestaJSON);
			}

			OutputStreamWriter wr = new OutputStreamWriter(uc.getOutputStream());
			wr.write(push.toString());

			if (uc.getResponseCode() != 200) {
				throw new Exception(uc.getResponseMessage());
			}
			InputStream is = uc.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			uc.disconnect();

			logger.debug("The content was : {}", sb.toString());

		} catch (Exception e) {
			throw new Exception("Problem beim versenden der Nachricht." + e);
		}
		return idSession;
	}

}
