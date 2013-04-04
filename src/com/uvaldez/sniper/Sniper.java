/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvaldez.sniper;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ujvaldez
 */
public class Sniper {
    public enum sniperType{
        type1,type2,type3,type4
    }
    
    private sniperType _type;
    private Timer _timer;
    private Timer _timerReloading;
    private String _name;
    private int _period;
    private boolean _reloading;
    private int _reloadCounter;
    private final sniperType defaultSniper = _type.type1;
    private final String defaultName = "Uziel";
    
    public Sniper()
    {
        init();
        _timer = new Timer("sniperTimer");
    }
    
    public void init()
    {
        _type = defaultSniper;
        _name=defaultName;
        _period = 1000;
        _reloading = false;
    }
    
    public void startWalking(){ 
        _timer = new Timer("walkingTimer");
        _timer.schedule(new TimerTask(){
            public void run()
            {
                walk();
            }
        }, 100, _period);
    }
    public void stopWalking(){
        _timer.cancel();
        System.out.println("Stopped!!!");
    }
    public void startShooting()
    {
        _timer.cancel();
        _timer = new Timer("shootingTimer");
        if(!_reloading){
        _timer.schedule(new TimerTask(){
            public void run()
            {
                fire();
            }
        }, 100,_period);
        }
        else{
            System.out.println("I cant fire im reloading!!!");
            _timer.cancel();
        }
    }
    
    public void startReloading()
    {
        _timerReloading = new Timer("reloadingTimer");        
        _reloadCounter = 0;
        _reloading=true;
        _timer.cancel();
        _timerReloading.schedule(new TimerTask(){
            public void run(){
                reload();
                _reloadCounter++;
                if(_reloadCounter == 5)
                {
                    _reloading = false;
                    _timerReloading.cancel();
                    startWalking();
                }
            }
        },100,_period);
    }
    
    public void startCrouching()
    {
        System.out.print("Crouch");
    }
    public void reload()
    {
        System.out.println("Im Reloading...");
    }
    public void walk()
    {
        System.out.println("Im Walking...");
    }
    public void run()
    {
        System.out.println("Im Running...");
    }
    public void aim()
    {
        System.out.println("Im Aiming...");
    }
    public void fire()
    {
        System.out.println("Im Firing...");
    }
    public void setName(String name)
    {
        _name = name;
    }
}
