package com.reyndev.brickbreaker.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.reyndev.brickbreaker.Main
import com.reyndev.brickbreaker.gameobject.GameObject

class GameScreen(private val game: Main) : Screen {
    private lateinit var stage: Stage
    private lateinit var layout: Table

    // Default screen resolution
    private var width: Float = 720f
    private var height: Float = 1600f

    // Game
    private var newStart: Boolean = true

    // Player
    private lateinit var player: GameObject

    // Ball
    private lateinit var ball: GameObject
    private lateinit var ballDirection: Vector3
    private var ballVelocity: Float = 0f

    init {
        // Stage init
        stage = Stage(ExtendViewport(width, height, OrthographicCamera()), game.batch)
        Gdx.input.inputProcessor = stage

        width = stage.viewport.worldWidth
        height = stage.viewport.worldHeight

        // Layout init
        layout = Table()
            .also {
                it.setFillParent(true)
                it.align(Align.top)
                stage.addActor(it)
            }

        // Player init
        player = GameObject(
            Texture("gfx/paddle.png"),
            256f,
            32f,
            (width - 256f) / 2f,
            200f
        )

        // Ball init
        ball = GameObject(
            Texture("gfx/ball.png"),
            32f,
            32f,
            (width - 32f) / 2f,
            player.position.y + 32f
        )
        ballDirection = Vector3()
        ballVelocity = 5f
    }

    override fun show() {}

    override fun render(delta: Float) {
        // Clear frame buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ScreenUtils.clear(0f, 0f, 1f, 1f)

        // Draw stage
        stage.act(delta)
        stage.draw()

        // Batch
        stage.batch.begin()
        player.update(stage.batch)
        ball.update(stage.batch)
        stage.batch.end()

        // Input
        if (Gdx.input.isTouched) {
            // Touch position
            val touchPos = Vector3()
                .also {
                    it.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
                    stage.camera.unproject(it)
                }

            if (newStart) {
                ballDirection = Vector3(2f, 3f, 0f)

                newStart = false
                return
            }

            player.position.x = touchPos.x - (player.width / 2f)
        }

        // Update

        // Player
        if (player.position.x < 0f)
            player.position.x = 0f
        if (player.position.x > (width - player.width))
            player.position.x = width - player.width

        // Ball
        ball.move(ballDirection)

        if (ball.position.x < 0f || ball.position.x > (width - ball.width))
            ballDirection.x *= -1f
        if (ball.position.y < 0f || ball.position.y > (height - ball.height))
            ballDirection.y *= -1f
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        stage.dispose()
        player.dispose()
        ball.dispose()
    }
}
