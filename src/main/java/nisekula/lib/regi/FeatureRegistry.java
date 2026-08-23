package nisekula.lib.regi;

import java.util.HashMap;
import java.util.Map;

public class FeatureRegistry {

    // 生成処理を行う関数型インターフェース（ラムダ式で処理を渡せる）
    @FunctionalInterface
    public interface Generator {
        /**
         * 地形生成のロジック
         * @param world ワールドデータ（マップの配列など）
         * @param x 生成中心X座標
         * @param y 生成中心Y座標
         */
        void generate(Object world, int x, int y);
    }

    // --- 1. 生成の配置ルール・条件 (Properties) ---
    public static class Properties {
        public float chance = 0.1f;    // 生成確率 (0.0 ~ 1.0)
        public int minY = 0;           // 生成最小高度
        public int maxY = 256;         // 生成最大高度
        public int veinSize = 8;       // 鉱脈サイズや構造物の規模

        // ビルダーパターン風の短縮設定
        public Properties ch(float chance) { this.chance = chance; return this; }
        public Properties height(int min, int max) { this.minY = min; this.maxY = max; return this; }
        public Properties size(int size) { this.veinSize = size; return this; }
    }

    // --- 2. フィーチャー（機能・構造物）データ ---
    public static class Feature {
        public final int id;
        public final String name;
        public final Properties props;
        public final Generator generator;

        public Feature(int id, String name, Properties props, Generator generator) {
            this.id = id;
            this.name = name;
            this.props = props;
            this.generator = generator;
        }

        /** 生成処理を実行 */
        public void place(Object world, int x, int y) {
            if (generator != null) {
                generator.generate(world, x, y);
            }
        }
    }

    private static final Map<Integer, Feature> FEATURES = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    // --- 登録関数 (r) ---
    public static Feature r(int id, String name, Properties props, Generator generator) {
        Feature f = new Feature(id, name, props, generator);
        FEATURES.put(id, f);
        NAME_TO_ID.put(name, id);
        return f;
    }

    // --- 取得関数 (g) ---
    public static Feature g(int id) {
        return FEATURES.get(id);
    }

    public static Feature g(String name) {
        Integer id = NAME_TO_ID.get(name);
        return id != null ? g(id) : null;
    }
}
