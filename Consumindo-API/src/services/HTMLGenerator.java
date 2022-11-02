package services;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import entities.Movies;

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
    public void generate(List<Movies> moviesList){
        //make a writer to write into html file
        PrintWriter output = new PrintWriter(file, true);

        output.write("""
            <body>

        """);
        
        for(Movies movie : moviesList){
            // write the title
            output.write(title(movie));
            // show the image
            output.write(image(movie));
            // write the description (rating and year)
            output.write(description(movie));

        }            
        output.write("""

                </body>
                """);
        output.close();
    }


    // Aux methods
    private String title(Movies movie){
        String title = """
                    <h3>
                    """ + movie.title() + """
                    </h3>
                    
                    """;
        return title;
    }

    private String image(Movies movie){
        String image = """
            <img src="
            """ + movie.urlImage() +"""
                ">

            """;
        return image;
    }

    private String description(Movies movie){
        String description = """
                <p> Nota: 
                """ + movie.rating() + " - Ano: " + movie.year() + "</p>" + """
                        
                        """;
        return description;
    }
}
