
   public class internalComparison {

       String title;
       String url;
       String img;

       // constructor
       public internalComparison(){

      }
       public void setTitle(String markTitle) {
          title = markTitle;
      }
       public void setUrl(String markUrl){
           url = markUrl;
       }
       public void setImg(String markImg){
           img = markImg;
       }

      public void printMark(){

          if (!(title == null))
            System.out.println( "TITLE: " + title );

          if (!(url == null))
              System.out.println( "URL: " + url );

          if (!(img == null))
              System.out.println( "IMG: " + img );

         // System.out.println( priority );

      }
   }
