package nisekula.world.gene.sys;

import java.util.HashMap;
import java.util.Map;

public class WorldgenSystems {

    // --- 1. バイオームおよびディメンションのデータ構造 ---
    public static class BiomeType {
        public final int id;
        public final String name;
        public final float paramA; // 追加パラメータ（湿度や温度、あるいは独自設定用）
        public final float paramB;

        public BiomeType(int id, String name) {
            this.id = id;
            this.name = name;
            this.paramA = 0.0f;
            this.paramB = 0.0f;
        }

        public BiomeType(int id, String name, float paramA, float paramB) {
            this.id = id;
            this.name = name;
            this.paramA = paramA;
            this.paramB = paramB;
        }
    }

    public static class DimensionType {
        public final int id;
        public final String name;
        public final int baseHeight;

        public DimensionType(int id, String name, int baseHeight) {
            this.id = id;
            this.name = name;
            this.baseHeight = baseHeight;
        }
    }

    // --- 2. レジストリ用マップ ---
    private static final Map<Integer, BiomeType> BIOMES = new HashMap<>();
    private static final Map<Integer, DimensionType> DIMENSIONS = new HashMap<>();

    // --- 3. バイオームのレジストリ関数 (オーバーロード対応) ---
    public static BiomeType registerBiome(int id, String name) {
        BiomeType biome = new BiomeType(id, name);
        BIOMES.put(id, biome);
        return biome;
    }

    public static BiomeType registerBiome(int id, String name, float paramA, float paramB) {
        BiomeType biome = new BiomeType(id, name, paramA, paramB);
        BIOMES.put(id, biome);
        return biome;
    }

    // ForestBiomeなどで使われている 3引数や4引数に対応するオーバーロード
    public static BiomeType registerBiome(int id, String name, int a, int b, int c) {
        BiomeType biome = new BiomeType(id, name, (float)a, (float)b);
        BIOMES.put(id, biome);
        return biome;
    }

    // --- 4. ディメンションのレジストリ関数と初期化 ---
    public static DimensionType registerDimension(int id, String name, int baseHeight) {
        DimensionType dim = new DimensionType(id, name, baseHeight);
        DIMENSIONS.put(id, dim);
        return dim;
    }

    // ディメンションIDの定数定義
    public static final int DIMENSION_OVERWORLD = 0;
    public static final int DIMENSION_CAVE      = 1;

    /**
     * ワールド生成システムの初期化
     * ディメンションの登録を行います
     */
    public static void init() {
        // ディメンションの登録
        registerDimension(DIMENSION_OVERWORLD, "overworld", 256);
        registerDimension(DIMENSION_CAVE, "cave_dimension", 128);
    }

    public static BiomeType getBiome(int id) {
        return BIOMES.get(id);
    }

    public static DimensionType getDimension(int id) {
        return DIMENSIONS.get(id);
    }
}
