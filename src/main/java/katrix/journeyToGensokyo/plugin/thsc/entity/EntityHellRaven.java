/**
 * This class was created by <Katrix>. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 * 
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */

package katrix.journeyToGensokyo.plugin.thsc.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import katrix.journeyToGensokyo.JourneyToGensokyo;
import katrix.journeyToGensokyo.handler.ConfigHandler;
import katrix.journeyToGensokyo.reference.EntityName;
import katrix.journeyToGensokyo.reference.MobID;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import thKaguyaMod.ShotData;
import thKaguyaMod.THShotLib;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.init.THKaguyaItems;

public class EntityHellRaven extends EntityTHFairy {

	public EntityHellRaven(World world) {
		super(world);
		
        this.setSize(1.3F, 1.2F);
    	lastTime = 0;
    	
    	experienceValue = 10;
    	
    	setDanmakuPattern(rand.nextInt(2) + 1);
    	shotColor = THShotLib.BLUE;
    	
        this.setMaxHP(15.0F);
        this.setHealth(15.0F);
        this.isImmuneToFire = true;
    	
    	lostTarget = 0;
    	this.setSpeed(0.3D);
    	this.setSpecies(EntityHellRaven.SPECIES_YOUKAI);
    	isFlyingMode = true;
    	
    	this.setAttackDistance(16.0D);
    	this.setDetectionDistance(16.0D);
    	this.setFlyingHeight(4);
	}
	
    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
    {
        Object p_110161_1_1 = super.onSpawnWithEgg(entityLivingData);
        return (IEntityLivingData)p_110161_1_1;
    }
    
    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 20)
        {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
        
    	if(this.deathTime == 7)
    	{
    		THShotLib.explosionEffect2(worldObj, posX, posY, posZ, 1.0F + deathTime * 0.1F);
    		THShotLib.banishExplosion(this, 5.0F, 5.0F);
    	}
    }
    
    @Override
    public void danmakuPattern(int level)
    {
    	Vec3 angle;
    	angle = THShotLib.angle(rotationYaw,  rotationPitch);
    	ShotData shotData;
    	
		switch(getDanmakuPattern())
		{
			case NORMAL_ATTACK01:
				danmakuSpan = 81;
				shotForm = THShotLib.FORM_KUNAI;
				speedRate = 0.25F * level;
		    	shotData = ShotData.shot(shotForm, shotColor, 0, 60, special);
		    	int pattern = 6;
		    	
				danmaku01(angle, shotData, level, pattern);
				break;
			case NORMAL_ATTACK02:
		    	danmakuSpan = 45;
		    	shotForm = THShotLib.FORM_SMALL;
		    	speedRate = 0.5F;
		    	shotData = ShotData.shot(shotForm, shotColor, 0, 80, special);
		    	angle = THShotLib.angle_LimitRandom(angle, THKaguyaConfig.danmakuAccuracy);
		    	
				danmaku02(angle, shotData, level);
				break;
			default:
				break;
		}
    }
    
    public void danmaku01(Vec3 angle, ShotData shotData, int level, int d)
    {  	
    	
		if (attackCounter <= 60 && attackCounter >= 2){
			
	    	THShotLib.playShotSound(this);
					
			int way = (d % 100 + 1);
			int num = (d / 100 + 1);
			double speed = speedRate;
			
			if(level == 1) {
				for(int i = 0; i < num; i++)
				{
					THShotLib.createSphereShot(getShooter(), pos(), angle, speed, shotData, way, 0F);
				}
			}
			
			if(level < 4) {
				for(int i = 0; i < num; i++)
				{
					THShotLib.createSphereShot(getShooter(), pos(), angle, speed, shotData, way, 0F);
				}
			}
			else {
				for(int i = 0; i < num; i++)
				{
					THShotLib.createSphereShot(getShooter(), pos(), angle, speed, shotData, way, 0F);
					THShotLib.createSphereShot(getShooter(), getShooter(), pos(), angle, 0F, speed, speed, 0D, gravity_Zero(),shotData, way, 0.5D, 0F);
				}
			}

		}
				
		if (attackCounter == 1){
					
		    if(rand.nextInt(2) == 0)
		    {
		    	this.moveRight(rand.nextDouble() * 0.3D + 0.3D, 32);
		    }
		    else
		    {
		    	this.moveLeft(rand.nextDouble() * 0.3D + 0.3D, 32);
		    }
		}
	}
    
    public void danmaku02(Vec3 angle, ShotData shotData, int level)
    {  	
    	int num = 1 + level;
		if (attackCounter == 10 || attackCounter == 20 || attackCounter == 30){
			
	    	THShotLib.playShotSound(this);
			
	    	for(double i = 0; i < num; i++){
	    		THShotLib.createShot(getShooter(), pos(), angle, (double)speedRate * (1 + i/2), (double)speedRate * (i/2), 0.0D, shotData);
	    	}
		}
				
		if (attackCounter == 31){
					
		    if(rand.nextInt(2) == 0)
		    {
		    	this.moveRight(rand.nextDouble() * 0.5D + 0.3D, 20);
		    }
		    else
		    {
		    	this.moveLeft(rand.nextDouble() * 0.5D + 0.3D, 20);
		    }
		}
	}
    
    public boolean attackEntityFrom(DamageSource damageSource, float amount)
    {
    	if(!damageSource.isMagicDamage()){
    		amount *= 0.5F;
    	}
        return super.attackEntityFrom(damageSource, amount);
    }
	
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
    
    @Override
    protected boolean canFairyCall()
    {
    	return false;
    }
    
    @Override
    protected double getFairyCallDistance()
    {
    	return 0.0D;
    }
    
	@Override
    protected Item getDropItem()
    {
    	return THKaguyaItems.power_item;
    }
    
	@Override
	protected void dropFewItems(boolean hasBeenAttackedByPlayer, int lootingLevel)
    {
		if(hasBeenAttackedByPlayer)
		{	
		    this.dropPowerUpItem(this.rand.nextInt(5) + this.rand.nextInt(3 + lootingLevel));
	        this.dropPointItem(this.rand.nextInt(5) + this.rand.nextInt(3 + lootingLevel));
		}
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
    	if(rand.nextInt(100) < THKaguyaConfig.fairySpawnRate)
    	{
    		return false;
    	}
    	
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
    }
    
    public static void postInit() {
    	
    	EntityRegistry.registerModEntity(EntityHellRaven.class, EntityName.HELL_RAVEN,  MobID.HELL_RAVEN, JourneyToGensokyo.instance, 80, 1, true);
		
		List<BiomeGenBase> spawnbiomes = new ArrayList<BiomeGenBase>(Arrays.asList(BiomeDictionary.getBiomesForType(Type.NETHER)));
		
		if(THKaguyaConfig.spawnDanmakuMob && ConfigHandler.newMobsSpawn){
			EntityRegistry.addSpawn(EntityHellRaven.class, 20, 1, 3, EnumCreatureType.monster, spawnbiomes.toArray(new BiomeGenBase[0]));
		}
    }
}
