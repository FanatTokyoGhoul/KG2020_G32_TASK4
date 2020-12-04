package kg2019examples_task4threedimensions.obj;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.util.ArrayList;
import java.util.List;

public class Obj implements IModel {
    private ArrayList<Vector3> v = new ArrayList<>(); // Вершины
    private ArrayList<Vector3> vn = new ArrayList<>(); // Нормали

    private ArrayList<ArrayList<Integer>> fPolygon = new ArrayList<>(); //Карта вершин, как их соединять
    private ArrayList<ArrayList<Integer>> fNormals = new ArrayList<>(); //Карта нормалей, как их соединять
    private ArrayList<ArrayList<Integer>> fTextures = new ArrayList<>(); //Карта текстур, как их соединять

    public ArrayList<Vector3> getVertices() {
        return v;
    }

    public ArrayList<Vector3> getNormals() {
        return vn;
    }

    public ArrayList<ArrayList<Integer>> getMapPolygon() {
        return fPolygon;
    }

    public ArrayList<ArrayList<Integer>> getMapNormals() {
        return fNormals;
    }

    public ArrayList<ArrayList<Integer>> getMApTextures() {
        return fTextures;
    }

    @Override
    public List<PolyLine3D> getLines() {
        ArrayList<PolyLine3D> list = new ArrayList<>();
        for(ArrayList<Integer> a:fPolygon){
            ArrayList<Vector3> vector3s = new ArrayList<>();
            for(Integer mask: a){
                vector3s.add(v.get(mask - 1));
            }
            list.add(new PolyLine3D(vector3s, true));
        }
        return list;
    }
}
