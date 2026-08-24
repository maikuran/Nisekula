package nisekula.lib.regi;

import java.util.HashMap;
import java.util.Map;

public class FluidRegistry {

    // --- 流体データの構造 ---
    public static class FluidType {
        public final int id;
        public final String name;
        public final boolean replacesAir; // 空気と置き換わる・空気ブロック扱いするかどうか

        public FluidType(int id, String name, boolean replacesAir) {
            this.id = id;
            this.name = name;
            this.replacesAir = replacesAir;
        }
    }

    private static final Map<Integer, FluidType> FLUIDS = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    /**
     * 流体を登録する
     * @param id 流体ID
     * @param name 流体名
     * @param replacesAir 空気と置き換わるか (trueの場合、空気と同様の透過・置換処理を行う)
     */
    public static FluidType r(int id, String name, boolean replacesAir) {
        FluidType type = new FluidType(id, name, replacesAir);
        FLUIDS.put(id, type);
        NAME_TO_ID.put(name, id);
        return type;
    }

    public static FluidType g(int id) {
        return FLUIDS.get(id);
    }

    public static FluidType g(String name) {
        Integer id = NAME_TO_ID.get(name);
        return id != null ? g(id) : null;
    }

    /**
     * 指定された流体が「空気と置き換わる（または空気の代わりに配置できる）性質」を持っているか判定
     */
    public static boolean canReplaceAir(int fluidId) {
        FluidType type = g(fluidId);
        return type != null && type.replacesAir;
    }
}
