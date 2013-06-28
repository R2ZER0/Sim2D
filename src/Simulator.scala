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

	def addParticle(particle:Particle) = {
		particles = particles + particle
		this
	}

	def stepAuto() {}

	def step(timeStep:Double) {
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
	def resolveCollision(A:Particle, B:Particle) {
		// Check that we really have collided
		if(!(A overlapsWith B)) return

		// Roll back until on the verge of collision
		// (See random sheet of paper with A picture of A vase on the back for formula)
		val C = A.position - B.position
		val D = A.velocity - B.velocity

		val cd = C dot D

		val kPart = sqrt( cd*cd - D.magnitudeSquared*C.magnitudeSquared*(A.radius + B.radius) )

		val k1 = (-cd + kPart)/D.magnitudeSquared
		val k2 = (-cd - kPart)/D.magnitudeSquared

		val k = if (k1 < 0) k1 else  k2

		A.position = A.position + A.velocity * k
		B.position = B.position + B.velocity * k

		// Reflect the velocities... extremely simple and most likely totally wrong..
		// probably have to deal with mass somehow
		val reflectInNormalised = (A.position - B.position).normalise

		A.velocity = -A.velocity + reflectInNormalised * (2 * (A.velocity dot reflectInNormalised))
		A.velocity = -B.velocity + reflectInNormalised * (2 * (B.velocity dot reflectInNormalised))

		// Step forward again with new velocities
		A.position = A.position - A.velocity * k
		B.position = B.position - B.velocity * k
	}

}
