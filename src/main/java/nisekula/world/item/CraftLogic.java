package nisekula.world.item;

import java.util.HashMap;
import java.util.Map;

public class CraftLogic {

    // クラフトレシピの定義データ（必要素材のマップ: アイテムID -> 必要個数）
    public static class Recipe {
        public final int resultItemId;
        public final int resultCount;
        public final Map<Integer, Integer> ingredients;

        public Recipe(int resultItemId, int resultCount, Map<Integer, Integer> ingredients) {
            this.resultItemId = resultItemId;
            this.resultCount = resultCount;
            this.ingredients = ingredients;
        }
    }

    // 登録された全レシピのリスト
    private static final Map<Integer, Recipe> RECIPES = new HashMap<>();
    private static int nextRecipeId = 1;

    /**
     * シェイプなし（手元）クラフトレシピを登録する
     * @param resultId 完成品アイテムID
     * @param resultCount 完成品個数
     * @param requiredItems 必要とするアイテムと個数のペア
     */
    public static void registerRecipe(int resultId, int resultCount, Map<Integer, Integer> requiredItems) {
        RECIPES.put(nextRecipeId++, new Recipe(resultId, resultCount, requiredItems));
    }

    /**
     * 手元のインベントリ（配列等）にあるアイテムを消費してクラフトを試行する
     * 
     * @param inventoryStorage インベントリ内の全アイテムID配列 (896スロット)
     * @param targetRecipe 試行するレシピ
     * @return クラフトに成功したら true
     */
    public static boolean craftItem(int[] inventoryStorage, Recipe targetRecipe) {
        if (inventoryStorage == null || targetRecipe == null) return false;

        // 1. まず素材が十分に揃っているかチェック
        for (Map.Entry<Integer, Integer> entry : targetRecipe.ingredients.entrySet()) {
            int requiredId = entry.getKey();
            int requiredCount = entry.getValue();

            int foundCount = 0;
            for (int itemId : inventoryStorage) {
                if (itemId == requiredId) {
                    foundCount++;
                }
            }

            if (foundCount < requiredCount) {
                return false; // 素材が足りない
            }
        }

        // 2. 素材をインベントリから消去（消費）する
        for (Map.Entry<Integer, Integer> entry : targetRecipe.ingredients.entrySet()) {
            int requiredId = entry.getKey();
            int remainingToRem = entry.getValue();

            for (int i = 0; i < inventoryStorage.length; i++) {
                if (inventoryStorage[i] == requiredId) {
                    inventoryStorage[i] = 0; // スロットを空にする
                    remainingToRem--;
                    if (remainingToRem <= 0) break;
                }
            }
        }

        // 3. 完成品をインベントリに追加する（スタック概念がないため、空きスロットに1個ずつ配置）
        int added = 0;
        for (int i = 0; i < inventoryStorage.length; i++) {
            if (inventoryStorage[i] == 0) {
                inventoryStorage[i] = targetRecipe.resultItemId;
                added++;
                if (added >= targetRecipe.resultCount) break;
            }
        }

        return true;
    }

    public static Map<Integer, Recipe> getAllRecipes() {
        return RECIPES;
    }
}
