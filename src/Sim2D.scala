import java.awt.Dimension
import scala.swing.{MainFrame, Frame}
import com.github.nscala_time.time.Imports._

/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 28/06/13
 * Time: 21:51
 */
object Sim2D extends App {

	val particleA = new Particle(
		Vector(150, 100),
		Vector(-1, 0),
		20
	)

	val particleB = new Particle(
		Vector(50, 100),
		Vector(1.2, 0),
		20
	)

	val sim = new Simulator().addParticle(particleA).addParticle(particleB)

	val top = new MainFrame {
		contents = new SimulatorPanel(sim) {
			preferredSize = new Dimension(300, 300)
		}

	}

	override def main(args: Array[String]) {
		super.main(args)
		top.visible = true
	}
}
