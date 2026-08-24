package nisekula.world.fluid;

import nisekula.lib.regi.FluidRegistry;

public class Fluids {

    // --- 流体IDの定義 ---
    public static final int WATER = 1;
    public static final int LAVA  = 2;
    public static final int ACID  = 3;

    /**
     * 流体の一括登録処理
     */
    public static void init() {
        // 水 (ID: 1)
        // 透明度: 0.2f (向こう側がよく透けて見える、青みがかった透明)
        // replacesAir = true (空気と置き換わって流体として満たされる)
        FluidRegistry.r(WATER, "water", true);

        // マグマ (ID: 2)
        // 透明度: 0.9f (ドロドロしていてあまり向こう側が見えない、発光・高粘度)
        // replacesAir = true
        FluidRegistry.r(LAVA, "lava", true);

        // 酸 (ID: 3)
        // 透明度: 0.5f (独特の濁りや怪しい光を放つ特殊な液体)
        // replacesAir = true
        FluidRegistry.r(ACID, "acid", true);
    }
}
