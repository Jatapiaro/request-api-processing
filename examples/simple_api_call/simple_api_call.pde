import http.requests.PostRequest;

/*
* To get your api key, just go to: https://azure.microsoft.com/en-us/services/cognitive-services/face/
* Then click on "Try Face API"
* Scroll Down Until You Find Face API, and click on "Get API key"
* Accept the termns and conditions and please in your country choose México
* Complete a login with fb or google
* Once it will show you a text that has key 1 or key 2. Choose one of them and paste it 
* NOTE: Key 1: e51672da5bf742b2a1c000e819a7dddd just copy --> e51672da5bf742b2a1c000e819a7dddd
*/

void setup() {
  String yourApiKey = "you_must_paste_your_key_here";
  try {
    PostRequest p = new PostRequest(
      "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceAttributes=emotion"
    );
    p.addHeader("Content-Type", "application/json");
    p.addHeader("Ocp-Apim-Subscription-Key", yourApiKey);
    p.addBodyParam("url", "http://wrmx00.epimg.net/programa/imagenes/2016/05/31/martha_debayle/1464712973_245053_1464723978_noticia_normal.jpg");
    p.sendRequest();
    int x = 1000000;
    while(x != 0) {
      x--;
    }
    println(p.getResponseValue());
  } catch(Exception e) {
    println(e.getMessage());
  }
}