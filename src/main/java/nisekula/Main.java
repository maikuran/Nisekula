package nisekula;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import nisekula.world.block.Blocks;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    private long window;
    private final int width = 800;
    private final int height = 600;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        init();
        loop();

        // 終了処理
        glfwDestroyWindow(window);
        glfwTerminate();
        GLFWErrorCallback cb = glfwSetErrorCallback(null);
        if (cb != null) cb.free();
    }

    private void init() {
        // エラー出力のセットアップ
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("GLFWの初期化に失敗しました。");
        }

        // ウィンドウの設定
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Nisekula - 2D Minecraft", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("GLFWウィンドウの作成に失敗しました。");
        }

        // OpenGLコンテキストの作成
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // 垂直同期(VSync)有効
        glfwShowWindow(window);

        GL.createCapabilities();

        // 背景色の設定 (マイクラの空色っぽい水色)
        glClearColor(0.5f, 0.8f, 1.0f, 1.0f);

        // --- ブロックデータの初期化 ---
        Blocks.init();
        System.out.println("nisekula initialized successfully!");
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            // 画面クリア
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // --- ゲームの描画処理をここに記述 ---
            render();

            // 画面の更新とイベント処理
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void render() {
        // ※ゲーム本編のレンダリング（マップやプレイヤー）をここに呼び出す

        // --- Copyright 表示 ---
        drawCopyright();
    }

    /**
     * 画面下部に著作権テキストを表示する処理
     */
    private void drawCopyright() {
        // 開発用ログ確認用（グラフィック文字描画ライブラリを組んだ際に画面へ描画します）
        // Copyright (C) 2026 maikuran. All Rights Reserved.
    }
}
