import java.awt.Dimension
import scala.swing.{MainFrame, Frame, SimpleSwingApplication}

/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 28/06/13
 * Time: 21:51
 */
object Sim2D extends SimpleSwingApplication {

	val particleA = new Particle(
		Vector(100, 100),
		Vector(-1, 0),
		20
	)

	val particleB = new Particle(
		Vector(50, 100),
		Vector(1.2, 0),
		20
	)

	val sim = new Simulator().addParticle(particleA).addParticle(particleB)

	def top = new MainFrame {
		contents = new SimulatorPanel(sim) {
			preferredSize = new Dimension(300, 300)
		}
	}
}
