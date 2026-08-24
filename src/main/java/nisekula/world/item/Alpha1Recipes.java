package nisekula.world.item;

import nisekula.world.block.Blocks;
import nisekula.world.item.CraftLogic.Recipe;
import java.util.HashMap;
import java.util.Map;

public class Alpha1Recipes {

    /**
     * Alpha 1.0 の全クラフトレシピを初期化・登録する
     */
    public static void init() {
        // --- 1. ジュースのレシピ (果実 2個 -> ジュース 3個) ---
        // リンゴ(402) 2個 -> リンゴジュース(405) 3個
        addSimpleRecipe(FoodItem.APPLE_JUICE, 3, FoodItem.APPLE, 2);
        // ミカン(403) 2個 -> ミカンジュース(404) 3個
        addSimpleRecipe(FoodItem.ORANGE_JUICE, 3, FoodItem.ORANGE, 2);

        // --- 2. 原木から木材への変換 (原木 1個 -> 木材 4個など、お好みで調整可能) ---
        addSimpleRecipe(Blocks.APPLE_PLANKS, 4, Blocks.APPLE_LOG, 1);
        addSimpleRecipe(Blocks.ZELKOVA_PLANKS, 4, Blocks.ZELKOVA_LOG, 1);
        addSimpleRecipe(Blocks.REDWOOD_PLANKS, 4, Blocks.REDWOOD_LOG, 1);
        addSimpleRecipe(Blocks.MANDARIN_PLANKS, 4, Blocks.MANDARIN_LOG, 1);

        // --- 3. ツール・防具の自動生成レシピ (棒がないため、木材や鉱石素材から直接クラフト) ---
        // ティア 1〜5 に対応する素材IDと完成品IDの対応マッピング
        // 例として、全5段階（木、石、鉄、イエロークリスタル、ダイヤ）をループで一括自動登録
        
        int[] tiers = {1, 2, 3, 4, 5};
        
        // 階級ごとの素材アイテム（またはブロック）IDの割り当て
        // 1:木(木材), 2:石, 3:鉄, 4:イエロークリスタル, 5:ダイヤ
        int[] materialIds = {
            Blocks.APPLE_PLANKS, // 簡易的に木材を代表とする
            Blocks.STONE, 
            OreItems.IRON_ORE_ITEM, 
            OreItems.YELLOW_CRYSTAL_ORE_ITEM, 
            OreItems.DIAMOND_ORE_ITEM
        };

        for (int i = 0; i < tiers.length; i++) {
            int tier = tiers[i];
            int matId = materialIds[i];

            // ツール (Wand) の自動レシピ (仮に木材/鉱石 2個でWand 1本など)
            // ※WandItemは今後作成されるため、IDのベース規則（例: 1, 2...）やプレースホルダーとして定義
            int wandId = 10 + tier; // 仮のWand ID
            addSimpleRecipe(wandId, 1, matId, 2);

            // 鎧 (Armor): 必要数 8個
            int armorId = 301 + i; // Wood, Stone, Iron, YellowCrystal, Diamond Armor
            addSimpleRecipe(armorId, 1, matId, 8);

            // 兜 (Helmet): 必要数 6個
            int helmetId = 311 + i; // Wood, Stone, Iron, YellowCrystal, Diamond Helmet
            addSimpleRecipe(helmetId, 1, matId, 6);
        }
    }

    /**
     * 単一素材から特定個数を作るレシピを簡単に登録するヘルパー
     */
    private static void addSimpleRecipe(int resultId, int resultCount, int ingredientId, int ingredientCount) {
        Map<Integer, Integer> ingredients = new HashMap<>();
        ingredients.put(ingredientId, ingredientCount);
        CraftLogic.registerRecipe(resultId, resultCount, ingredients);
    }
}
