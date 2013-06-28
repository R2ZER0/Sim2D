/**
 * Created with IntelliJ IDEA.
 * User: rikki
 * Date: 26/06/13
 * Time: 16:22
 */
class Particle(var position:Vector = Vector.ZERO,
               var velocity:Vector = Vector.ZERO,
               var radius:Double = 1,
               var density:Double = 1)
{
	def move(timeStep:Double) {
		position = position + velocity * timeStep
	}

	def overlapsWith(that:Particle) = this.position.distanceTo(that.position) < this.radius + that.radius

	def applyForce(timeStep:Double, force:Vector) {
		velocity = velocity + force * timeStep / density //Scala's operator precedence means this works fine
	}

	override def clone = new Particle(position, velocity, radius, density)
	override def toString = "Particle(\" + this.getPosition() + \", \" + this.getVelocity() + \", \" + this.getRadius() + \")\""
}
