package nisekula.world.block;

import nisekula.lib.regi.BlockRegistry;
import static nisekula.lib.regi.BlockRegistry.r;

public class Blocks {

    // アトラスの1タイルのサイズとアトラス全体のマス数（例: 256x256画像で16x16ブロックなら16マス）
    public static final float ATLAS_GRID_SIZE = 16.0f; // 縦横16x16マス
    public static final float TILE_UV_SIZE = 1.0f / ATLAS_GRID_SIZE; // 1マスのUV幅 (0.0625f)

    // --- ブロックID定義 ---
    public static final int AIR = 0;
    
    // 原木類
    public static final int APPLE_LOG = 1;
    public static final int ZELKOVA_LOG = 2;   // ケヤキ
    public static final int REDWOOD_LOG = 3;   // セコイア
    public static final int MANDARIN_LOG = 4;  // ミカン
    
    // 木材類 (樹種別)
    public static final int APPLE_PLANKS = 5;
    public static final int ZELKOVA_PLANKS = 6;
    public static final int REDWOOD_PLANKS = 7;
    public static final int MANDARIN_PLANKS = 8;

    // 葉っぱ類 (樹種別)
    public static final int APPLE_LEAVES = 9;
    public static final int ZELKOVA_LEAVES = 10;
    public static final int REDWOOD_LEAVES = 11;
    public static final int MANDARIN_LEAVES = 12;

    // 基本自然・建築
    public static final int DIRT = 13;
    public static final int SOFT_DIRT = 14;
    public static final int SAND = 15;
    public static final int STONE = 16;
    public static final int DEEPSLATE = 17;
    public static final int BRICK = 18;

    // 鉱石類
    public static final int IRON_ORE = 19;
    public static final int YELLOW_CRYSTAL_ORE = 20;
    public static final int DIAMOND_ORE = 21;

    // 架空の岩石
    public static final int LUXALBSTONE = 22; // 白光岩

    /**
     * アトラス上のグリッド位置(gx, gy)からUV座標を計算して登録するヘルパー関数
     * @param gx アトラスのXマス目 (0 〜 15)
     * @param gy アトラスのYマス目 (0 〜 15)
     */
    private static void reg(int id, String name, boolean solid, float strength, int gx, int gy) {
        float u = gx * TILE_UV_SIZE;
        float v = gy * TILE_UV_SIZE;
        r(id, name, solid, strength, u, v, TILE_UV_SIZE, TILE_UV_SIZE);
    }

    /**
     * ブロックの一括登録処理
     */
    public static void init() {
        // Air (空気): 描画不要
        r(AIR, "air", false, 0.0f, 0f, 0f, 0f, 0f);

        // --- 原木 (左上エリア [0, 0] 付近に配置) ---
        reg(APPLE_LOG,    "apple_log",    true, 2.0f, 0, 0);
        reg(ZELKOVA_LOG,  "zelkova_log",  true, 2.0f, 1, 0);
        reg(REDWOOD_LOG,  "redwood_log",  true, 2.5f, 2, 0);
        reg(MANDARIN_LOG, "mandarin_log", true, 1.8f, 3, 0);

        // --- 木材 (Y=1 行目) ---
        reg(APPLE_PLANKS,    "apple_planks",    true, 1.5f, 0, 1);
        reg(ZELKOVA_PLANKS,  "zelkova_planks",  true, 1.5f, 1, 1);
        reg(REDWOOD_PLANKS,  "redwood_planks",  true, 1.8f, 2, 1);
        reg(MANDARIN_PLANKS, "mandarin_planks", true, 1.3f, 3, 1);

        // --- 葉っぱ (Y=2 行目) ※すり抜け可能にするなら solid = false
        reg(APPLE_LEAVES,    "apple_leaves",    false, 0.2f, 0, 2);
        reg(ZELKOVA_LEAVES,  "zelkova_leaves",  false, 0.2f, 1, 2);
        reg(REDWOOD_LEAVES,  "redwood_leaves",  false, 0.2f, 2, 2);
        reg(MANDARIN_LEAVES, "mandarin_leaves", false, 0.2f, 3, 2);

        // --- 地形・土・砂 (Y=3 行目) ---
        reg(DIRT,      "dirt",      true, 0.5f, 0, 3);
        reg(SOFT_DIRT, "soft_dirt", true, 0.3f, 1, 3);
        reg(SAND,      "sand",      true, 0.5f, 2, 3);

        // --- 石・建築 (Y=4 行目) ---
        reg(STONE,     "stone",     true, 1.5f, 0, 4);
        reg(DEEPSLATE, "deepslate", true, 3.0f, 1, 4);
        reg(BRICK,     "brick",     true, 2.0f, 2, 4);

        // --- 鉱石・架空石 (Y=5 行目) ---
        reg(IRON_ORE,           "iron_ore",           true, 3.0f, 0, 5);
        reg(YELLOW_CRYSTAL_ORE, "yellow_crystal_ore", true, 3.0f, 1, 5);
        reg(DIAMOND_ORE,        "diamond_ore",        true, 4.0f, 2, 5);
        reg(LUXALBSTONE,        "luxalbstone",        true, 2.5f, 3, 5);
    }
}
