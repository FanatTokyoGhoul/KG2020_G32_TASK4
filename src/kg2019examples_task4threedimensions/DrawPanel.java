/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import kg2019examples_task4threedimensions.draw.IDrawer;
import kg2019examples_task4threedimensions.draw.SimpleEdgeDrawer;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.obj.Obj;
import kg2019examples_task4threedimensions.obj.ObjLoader;
import kg2019examples_task4threedimensions.screen.ScreenConverter;
import kg2019examples_task4threedimensions.third.Camera;
import kg2019examples_task4threedimensions.third.Scene;
import models.Parallelepiped;

/**
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private Camera cam;
    private CameraController camController;
    private ObjLoader objLoader = new ObjLoader();
    private Button button = new Button("Load File");

    public DrawPanel() {
        super();
        add(button);
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new Camera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

//        scene.getModelsList().add(new Parallelepiped(
//                new Vector3(-0.4f, -0.4f, -0.4f),
//                new Vector3(0.4f, 0.4f, 0.4f)
//        ));

        scene.getModelsList().add(objLoader.loadObj("./Obj/SwordMinecraft.obj"));

        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);


        JFileChooser fileChooserObjOpen = new JFileChooser();/*Окошко для загрузки внешних файлов*/
        fileChooserObjOpen.setCurrentDirectory(new File("./Obj"));
        fileChooserObjOpen.addChoosableFileFilter(new FileNameExtensionFilter("Obj Files (*.obj)", "obj"));
        fileChooserObjOpen.setAcceptAllFileFilterUsed(false);
        fileChooserObjOpen.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserObjOpen.setApproveButtonText("Load");
        button.addActionListener(e -> {
            if (fileChooserObjOpen.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    scene.getModelsList().clear();
                    scene.getModelsList().add(objLoader.loadObj(fileChooserObjOpen.getSelectedFile()));
                    this.repaint();
                } catch (Exception exc) {
                    System.err.print("Ошибка загрузки файла!");
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}
