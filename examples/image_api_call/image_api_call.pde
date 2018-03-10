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
  String yourApiKey = "your-api-key";
  try {
    PostRequest p = new PostRequest(
      "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceAttributes=emotion"
    );
    p.addHeader("Content-Type", "application/octet-stream");
    p.addHeader("Ocp-Apim-Subscription-Key", yourApiKey);
    p.sendImageRequest("/Users/jacobotapia/Desktop/14933186321029.jpg");
    int x = 1000000;
    while(x != 0) {
      x--;
    }
    println(p.getResponseResult());
  } catch(Exception e) {
    println(e.getMessage());
  }
}
