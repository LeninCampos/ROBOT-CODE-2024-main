// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;

import java.util.Currency;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import frc.robot.Constants.MotorControllers;
import frc.robot.Constants.chassis;

public class drivetrain extends SubsystemBase {
  TalonFX rightLeader = new TalonFX(MotorControllers.rightLeader);
  TalonFX leftLeader = new TalonFX(MotorControllers.leftLeader);
  TalonFX rightFollower = new TalonFX(MotorControllers.rightFollower);
  TalonFX leftFollower = new TalonFX(MotorControllers.leftFollower);

  DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);


  CurrentLimitsConfigs currentLimit = new CurrentLimitsConfigs();

  TalonFXConfiguration leftConfiguration = new TalonFXConfiguration();
  TalonFXConfiguration rightConfiguration = new TalonFXConfiguration();

  public drivetrain() {

  currentLimit.SupplyCurrentLimitEnable = true;
  currentLimit.SupplyCurrentLimit = 40; 
  currentLimit.SupplyCurrentThreshold = 40; 
  currentLimit.SupplyTimeThreshold = 0.1; 

  leftConfiguration.CurrentLimits = currentLimit;
  rightConfiguration.CurrentLimits = currentLimit;
    
  rightLeader.getConfigurator().apply(rightConfiguration);
  leftLeader.getConfigurator().apply(leftConfiguration);
  rightFollower.getConfigurator().apply(rightConfiguration);
  leftFollower.getConfigurator().apply(leftConfiguration);

  rightLeader.setInverted(true);
  leftLeader.setInverted(false);

  leftLeader.setSafetyEnabled(true);
  leftFollower.setSafetyEnabled(true);
  rightLeader.setSafetyEnabled(true);
  rightFollower.setSafetyEnabled(true);

  leftFollower.setControl(new Follower(leftLeader.getDeviceID(), false));
  rightFollower.setControl(new Follower(rightLeader.getDeviceID(), false));
  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
  }

  

  public void resetEncoders(){
    leftLeader.setPosition(0);
    rightLeader.setPosition(0);
  }

  

  


}
