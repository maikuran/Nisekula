package nisekula.world.gene.sys;

import java.util.HashMap;
import java.util.Map;

public class WorldgenSystems {

    // --- 1. バイオームおよびディメンションのデータ構造 ---
    public static class BiomeType {
        public final int id;
        public final String name;
        public BiomeType(int id, String name) {
            this.id = id;
            this.name = name;
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

    // --- 3. バイオームのレジストリ関数 (登録はここでは空、あるいは外部から追加可能に) ---
    public static BiomeType registerBiome(int id, String name) {
        BiomeType biome = new BiomeType(id, name);
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
     * ディメンションの登録を行います（バイオームはこの時点では未登録）
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
