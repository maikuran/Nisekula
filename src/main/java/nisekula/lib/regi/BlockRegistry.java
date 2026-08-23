package nisekula.lib.regi;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    // ブロックの各種パラメータを保持するデータ構造
    public static class Block {
        public final int id;
        public final String name;
        public final boolean solid;    // 当たり判定
        public final float strength;   // 硬さ（破壊にかかる時間など）
        public final float u, v;       // テクスチャのアトラス座標 (0.0 ~ 1.0)
        public final float uw, vh;     // テクスチャの幅と高さ (UVサイズ)

        public Block(int id, String name, boolean solid, float strength, float u, float v, float uw, float vh) {
            this.id = id;
            this.name = name;
            this.solid = solid;
            this.strength = strength;
            this.u = u;
            this.v = v;
            this.uw = uw;
            this.vh = vh;
        }
    }

    private static final Map<Integer, Block> BLOCKS = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    // --- フルパラメータでの登録関数 (r) ---
    public static Block r(int id, String name, boolean solid, float strength, float u, float v, float uw, float vh) {
        Block b = new Block(id, name, solid, strength, u, v, uw, vh);
        BLOCKS.put(id, b);
        NAME_TO_ID.put(name, id);
        return b;
    }

    // --- 簡易登録用のオーバーロード (テクスチャサイズデフォルト版など) ---
    public static Block r(int id, String name, boolean solid, float strength, float u, float v) {
        return r(id, name, solid, strength, u, v, 0.25f, 0.25f); // 4x4アトラス前提のデフォルト
    }

    public static Block r(int id, String name, boolean solid, float strength) {
        return r(id, name, solid, strength, 0f, 0f, 0f, 0f);
    }

    // --- 取得関数 (g) ---
    public static Block g(int id) {
        return BLOCKS.get(id);
    }

    public static Block g(String name) {
        Integer id = NAME_TO_ID.get(name);
        return id != null ? g(id) : null;
    }

    /** 登録されている全ブロックのマップを取得 */
    public static Map<Integer, Block> getAll() {
        return BLOCKS;
    }
}
