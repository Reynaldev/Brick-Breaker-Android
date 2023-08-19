package com.reyndev.brickbreaker.gameobject

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector3

interface Controller {
    fun move(vec: Vector3)
    fun update(batch: Batch)
    fun collideWidth(other: GameObject): Boolean
    fun dispose()
}