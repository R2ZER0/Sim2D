import scala.collection.immutable.ListSet
import scala.math.sqrt

/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 26/06/13
 * Time: 16:52
 */
class Simulator {

	var particles:Set[Particle] = ListSet.empty

	val timeStep = 0.05

	def addParticle(particle:Particle) = {
		particles = particles + particle
		this
	}

	def step() {
		// Move all the particles
		particles.map { _.move(timeStep) } //Note to future self: remember _ is the implicit lambda argument, like Perl's $_

		for(particleA <- particles)
			for(particleB <- particles)
				if(particleA overlapsWith particleB)
					onCollision(particleA, particleB)
	}

	def onCollision(a:Particle, b:Particle) {
		resolveCollision(a, b)
	}

	// When particle p has collided with another particle, this updates p with it's new position/velocity
	def resolveCollision(a:Particle, b:Particle) {
		// Check that we really have collided
		if(!(a overlapsWith b)) return

		// Roll back until on the verge of collision
		// (See random sheet of paper with a picture of a vase on the back for formula)
		val c = a.position - b.position
		val d = a.velocity - b.velocity

		val cd = c dot d

		val kPart = sqrt( cd*cd - d.magnitudeSquared*c.magnitudeSquared*(a.radius + b.radius) )

		val k1 = (-cd + kPart)/d.magnitudeSquared
		val k2 = (-cd - kPart)/d.magnitudeSquared

		val k = if (k1 < 0) k1 else  k2

		a.position = a.position + a.velocity * k
		b.position = b.position + b.velocity * k

		// Reflect the velocities... extremely simple and most likely totally wrong..
		// probably have to deal with mass somehow
		val reflectInNormalised = (a.position - b.position).normalise

		a.velocity = -a.velocity + reflectInNormalised * (2 * (a.velocity dot reflectInNormalised))
		a.velocity = -b.velocity + reflectInNormalised * (2 * (b.velocity dot reflectInNormalised))

		// Step forward again with new velocities
		a.position = a.position - a.velocity * k
		b.position = b.position - b.velocity * k
	}

}
