package kg2019examples_task4threedimensions.obj;

import kg2019examples_task4threedimensions.math.Vector3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ObjLoader {
    public Obj loadObj(String path){
        return loadObj(new File(path));
    }
    public Obj loadObj(File file) {
        Obj obj = new Obj();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            scanner = new Scanner("");
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), " ", false);
            while (tokenizer.hasMoreTokens()) {
                switch (tokenizer.nextToken()) {
                    case "v":
                        obj.getVertices().add(new Vector3(Float.parseFloat(tokenizer.nextToken()), Float.parseFloat(tokenizer.nextToken()), Float.parseFloat(tokenizer.nextToken())));
                        break;
                    case "vn":
                        obj.getNormals().add(new Vector3(Float.parseFloat(tokenizer.nextToken()), Float.parseFloat(tokenizer.nextToken()), Float.parseFloat(tokenizer.nextToken())));
                        break;
                    case "f":

                        ArrayList<Integer> polygons = new ArrayList<>();
                        ArrayList<Integer> textures = new ArrayList<>();
                        ArrayList<Integer> normals = new ArrayList<>();

                        while (tokenizer.hasMoreElements()) {
                            String[] mask = tokenizer.nextToken().split("/");
                            if (!"".equals(mask[0])) {
                                polygons.add(Integer.parseInt(mask[0]));
                            }

//                            if (!"".equals(mask[1])) {
//                                textures.add(Integer.parseInt(mask[1]));
//                            }
//
//                            if (!"".equals(mask[2])) {
//                                normals.add(Integer.parseInt(mask[2]));
//                            }
                        }
                        obj.getMapPolygon().add(polygons);
                        obj.getMapNormals().add(normals);
                        obj.getMApTextures().add(textures);
                        break;
                    default:
                        break;
                }
            }

        }
        return obj;
    }
}
