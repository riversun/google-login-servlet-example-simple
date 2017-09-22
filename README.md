# Overview
It is licensed under [MIT](https://opensource.org/licenses/MIT).

## Example of OAuth2 servlet 

- Get OAuth2 credential(access_token,refresh_token)
- Access https://www.googleapis.com/oauth2/v2/userinfo to get **userinfo** with credential(access_token,refresh_token)

<img src="https://riversun.github.io/img/goauth2/lib_oauth2_example01.png">  

## How to import into your Eclipse and Run.

### Import into Eclipse

1.Select File>Import>Git - Projects from Git  

2.Clone URI  

3.set clone URI to https://github.com/riversun/google-login-servlet-example-simple.git

4.Select next along the flow  

5.Check "Import as general project" and select "finish"  

### Servlet

```java
public class MyAppServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		// Get credential including access_token
		GoogleCredential credential = OAuthSession.getInstance().createCredential(req);
		// Get unique userId
		String userId = OAuthSession.getInstance().getUserId(req);

		Oauth2 oauth2 = new Oauth2.Builder(
				new com.google.api.client.http.javanet.NetHttpTransport(),
				new com.google.api.client.json.jackson2.JacksonFactory(),
				credential).build();

		// Get userInfo using credential
		Userinfoplus userInfo = oauth2.userinfo().get().execute();
		final PrintWriter out = resp.getWriter();
		// Show result
		out.println("<html><body>You are already logged in to Google.");
		out.println("<br>");
		out.println("<b>OAuth2/OpenId connect result</b><br>");
		out.println("userId=" + userId);
		out.println("userInfo=" + userInfo);

		out.close();

	}

}
```

### Run example

1. Right click on imported project  
1. Select **Configure>Convert to Maven project**
(Now you can handle this project as a maven project) 

1. run **com.example.google_login.MyAppMain**

<img src="https://riversun.github.io/img/goauth2/lib_oauth2_example01.png">  


# More practical examples

In the above example you know authentication/authorization and application login are inseparable.

To create a practical web application,  
It is necessary to separate application level login and OAuth2-flow.  
And it is necessary to design separately the part of authentication "who are you?"  
and the part of authorization to grant permission (to the API).

## download / clone
I would like to introduce an example of separating app login and OAuth.You can clone and easy to run it.

https://github.com/riversun/google-login-servlet-example-on-jetty.git

# Helper Library for OAuth2 servlet

https://github.com/riversun/google-oauth2-client-servlet


