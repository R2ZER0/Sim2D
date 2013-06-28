import java.awt.Graphics2D
import scala.swing.Panel

/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 28/06/13
 * Time: 21:44
 */
class SimulatorPanel(sim:Simulator) extends Panel {

	override def paintComponent(g: Graphics2D) {
		for(particle:Particle <- sim.particles) {
			g.drawOval(particle.position.x.toInt,
					   particle.position.y.toInt,
					   particle.radius.toInt*2,
					   particle.radius.toInt*2)
		}
	}



}
