package epiris.tower.defense.cstockton.org.object;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.content.SharedPreferences;

import epiris.tower.defense.cstockton.org.bgmanager.BackgroundManager;
import epiris.tower.defense.cstockton.org.creep.CreepFloatingTextManager;
import epiris.tower.defense.cstockton.org.creep.CreepManager;
import epiris.tower.defense.cstockton.org.effect.EffectManager;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.game.GameOptions;
import epiris.tower.defense.cstockton.org.game.IGame;
import epiris.tower.defense.cstockton.org.map.Map;
import epiris.tower.defense.cstockton.org.player.LocalPlayer;
import epiris.tower.defense.cstockton.org.player.PlayerInteracter;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievements;
import epiris.tower.defense.cstockton.org.player.data.PlayerData;
import epiris.tower.defense.cstockton.org.player.rewards.PlayerRewards;
import epiris.tower.defense.cstockton.org.player.statistics.PlayerStatistics;
import epiris.tower.defense.cstockton.org.player.storage.PlayerStorageConnector;
import epiris.tower.defense.cstockton.org.spell.SpellManager;
import epiris.tower.defense.cstockton.org.tower.TowerManager;
import epiris.tower.defense.cstockton.org.ui.Hud;
import epiris.tower.defense.cstockton.org.wave.WaveManager;

final public class Registry {

    // runner
    static public IGame sGame;
    static public GameActivity sGameActivity;
    static public GameOptions sGameOptions;
    static public Context sContext;

    // scene
    static public Scene sScene;

    // scene entities
    static public IEntity sSceneLayerMap;
    static public IEntity sSceneLayerTowers;
    static public IEntity sSceneLayerCreeps;
    static public IEntity sSceneLayerEffects;
    static public IEntity sSceneLayerUi;

    // sprites textures
    static public BitmapTextureAtlas[] sSpriteTextures;
    static public TiledTextureRegion[] sSpriteTextureRegions;

    // creeps textures
    static public BitmapTextureAtlas[] sCreepsTextures;
    static public TiledTextureRegion[] sCreepsTextureRegions;

    // effects textures
    static public BitmapTextureAtlas[] sEffectsTextures;
    static public TiledTextureRegion[] sEffectsTextureRegions;

    // tower textures
    static public BitmapTextureAtlas[] sTowerTextures;
    static public TiledTextureRegion[] sTowerTextureRegions;

    // small ui texture region
    static public TiledTextureRegion sUiSmallTextureRegion;
    static public TiledTextureRegion sUiLargeTextureRegion;

    // game menu
    static public TextureRegion sTowerUpgraderBorderTextureRegion;
    //static public TextureRegion sGameMenuBgTextureRegion;
    static public TextureRegion sTowerCircleTextureRegion;

    // fonts
    static public Font sFontWhite8;
    static public Font sFontWhite12;
    static public Font sFontWhite16;
    static public Font sFontWhite24;
    static public Font sFontWhite32;
    static public Font sFontWhite48;

    // hud
    static public Hud sHud;

    // pools

    static public SharedPreferences sSharedPreferences;

    static public Map sMap;

    static public LocalPlayer sLocalPlayer;
    static public PlayerStorageConnector sPlayerStorageConnector;
    static public PlayerRewards sPlayerRewards;
    static public PlayerStatistics sPlayerStatistics;
    static public PlayerAchievements sPlayerAchievements;
    static public PlayerData sPlayerData;
    static public PlayerInteracter sPlayerInteracter;

    // managers
    static public CreepFloatingTextManager sCreepFloatingTextManager;
    static public BackgroundManager sBackgroundManager;
    static public EffectManager sEffectManager;
    static public TowerManager sTowerManager;
    static public CreepManager sCreepManager;
    static public WaveManager sWaveManager;
    static public SpellManager sSpellManager;

    // level stuff
    //static public LevelBuilder sLevelBuilder;
    //static public Level sLevel;

    // global floats
    static public float sFloatFastForward = 1f;
    //static public float sFloatZoomFactor = 1f;

}