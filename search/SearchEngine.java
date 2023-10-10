import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    List<String> listOfWords = new ArrayList<String>();
    
    public String handleRequest(URI url){
        if (url.getPath().equals("/")){
            String text = "";
            for (int i = 0; i < listOfWords.size(); i++){
                text += listOfWords.get(i);
                if (i < listOfWords.size()-1){
                    text += ", ";
                }
            }
            return String.format("%s", text);
        }
        else if (url.getPath().equals("/add")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")){
                listOfWords.add(parameters[1]);
                return String.format("%s", parameters[1]);
            }
        }
        else if (url.getPath().equals("/search")){
            String text = "";
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")){
                String wordToSearch = parameters[1];
                for (int j = 0; j < listOfWords.size(); j++){
                    if (listOfWords.get(j).contains(wordToSearch)){
                        text += listOfWords.get(j);
                        text += " ";
                    }
                }
                return String.format("%s", text);
            }
            return String.format("No strings have this substring");
        }
        
        return String.format("404 Not Found!");
        
    }
    /*
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Lauren's Number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }
    */
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
