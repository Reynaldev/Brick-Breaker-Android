package com.reyndev.brickbreaker

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.reyndev.brickbreaker.screen.MainMenuScreen

class Main : Game() {
    lateinit var engine: Engine
    lateinit var batch: SpriteBatch

    override fun create() {
        engine = Engine()
        batch = SpriteBatch()

        setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        engine.removeAllEntities()
        engine.removeAllSystems()

        batch.dispose()
    }
}