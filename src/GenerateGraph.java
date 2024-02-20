import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.*;
public class GenerateGraph {

    public static Graph drawGraph(int[][] input, String filePath) throws IOException {
        Graph g = graph("Graph").linkAttr().with("class", "link-class");
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                if(input[i][j] == 0){continue;}
                    g = g.with(node(String.valueOf(i))
                            .link(to(node(String.valueOf(j)))
                                    .with(attr("label", input[i][j]), Style.DASHED)));
            }
        }
        return g;
    }

    public static Graph generatePrimInitialGraph(int[][] input, int startingNode, String filePath) throws IOException {
        Graph g = drawGraph(input,filePath);
        g = g.with(node(Integer.toString(startingNode)).with(Label.markdown("start")));
        for (int i = 0; i < input.length; i++) {
            if(i == startingNode){continue;}
            g = g.with(node(Integer.toString(i)).with(Label.of("1000, "+i)));
        }
        saveGraph(g,filePath);
        return g;
    }

    public static void saveGraph(Graph g, String filePath) throws IOException {
        Graphviz.fromGraph(g).engine(Engine.CIRCO).render(Format.PNG).toFile(new File(filePath));
    }
}