package services;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import entities.Content;

public class HTMLGenerator {
    
    private Writer file;

    //Basic methods
    public HTMLGenerator(Writer file){
        this.file = file;   
    }

    public Writer getFile(){
        return this.file;
    }


    //unique methods
    public void generate(List<? extends Content> contentList){
        //make a writer to write into html file
        PrintWriter output = new PrintWriter(file, true);

        output.write("""
            <body>

        """);
        
        for(Content content : contentList){
            // write the title
            output.write(title(content));
            // show the image
            output.write(image(content));
            // write the description (rating and year)
            output.write(description(content));

        }            
        output.write("""

                </body>
                """);
        output.close();
    }


    // Aux methods
    private String title(Content content){
        String title = """
                    <h3>
                    """ + content.title() + """
                    </h3>
                    
                    """;
        return title;
    }

    private String image(Content content){
        String image = """
            <img src="
            """ + content.urlImage() +"""
                " style="height: 40%">

            """;
        return image;
    }

    private String description(Content content){
        String description = """
                <p> Nota: 
                """ + content.rating() + " - Ano: " + content.year() + "</p>" + """
                        
                        """;
        return description;
    }
}
