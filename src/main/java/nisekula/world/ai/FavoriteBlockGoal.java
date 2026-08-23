package nisekula.world.ai;

import nisekula.world.CoordinateData;
import nisekula.world.EntityData;

public class FavoriteBlockGoal {
    public int favoriteBlockId; // 大好きなブロックID
    public int searchRadius;    // 探索範囲

    public FavoriteBlockGoal(int favoriteBlockId, int searchRadius) {
        this.favoriteBlockId = favoriteBlockId;
        this.searchRadius = searchRadius;
    }

    public void tick(EntityData entity, CoordinateData[][] nearbyMap) {
        // 周囲のマップデータをスキャンして大好物ブロックへ近づく処理
    }
}
