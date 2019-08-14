package chapter_1_stackandqueue

import java.util.*

/**
 * 猫狗队列
 */
open class Pet(val type: String)

class Cat : Pet("cat")
class Dog : Pet("dog")

class PetWrapper(val pet: Pet, val index: Long)

interface IOperation {
    fun add(pet: Pet)
    fun pollAll(): List<Pet>
    fun pollCat(): Pet
    fun pollDog(): Pet
    fun isEmpty(): Boolean
    fun isCatEmpty(): Boolean
    fun isDogEmpty(): Boolean
}

class CatDogQueue : IOperation {
    private val catQueue = LinkedList<PetWrapper>()
    private val dogQueue = LinkedList<PetWrapper>()
    private var index: Long = 0L

    override fun add(pet: Pet) {
        when (pet.type) {
            "cat" -> {
                catQueue.add(PetWrapper(Cat(), index++))
            }
            "dog" -> {
                dogQueue.add(PetWrapper(Dog(), index++))
            }
            else -> {
                throw RuntimeException("Pet Type Error.Current type is ${pet.type}")
            }
        }
    }

    private fun pollSingle(): Pet {
        return if (!catQueue.isEmpty() && !dogQueue.isEmpty()) {
            if (catQueue.peek().index < dogQueue.peek().index) {
                catQueue.poll().pet
            } else {
                dogQueue.poll().pet
            }
        } else if (!catQueue.isEmpty()) {
            // 猫队列不为空，狗队列为空，直接返回猫队列的第一个元素
            catQueue.poll().pet
        } else if (!dogQueue.isEmpty()) {
            // 同上，返回狗队列的第一个元素
            dogQueue.poll().pet
        } else {
            throw RuntimeException("PetQueue is Empty.")
        }
    }

    override fun pollAll(): List<Pet> {
        val petList = LinkedList<Pet>()
        while (!isEmpty()) {
            petList.add(pollSingle())
        }
        return petList
    }

    override fun pollCat(): Pet {
        if (catQueue.isEmpty()) {
            throw RuntimeException("CatQueue is Empty.")
        }
        return catQueue.poll().pet
    }

    override fun pollDog(): Pet {
        if (dogQueue.isEmpty()) {
            throw RuntimeException("DogQueue is Empty.")
        }
        return dogQueue.poll().pet
    }

    override fun isEmpty(): Boolean {
        return catQueue.isEmpty() && dogQueue.isEmpty()
    }

    override fun isCatEmpty(): Boolean {
        return catQueue.isEmpty()
    }

    override fun isDogEmpty(): Boolean {
        return dogQueue.isEmpty()
    }
}

fun main() {
    val queue = CatDogQueue()

    val cat1 = Cat()
    val cat2 = Cat()
    val cat3 = Cat()

    val dog1 = Dog()
    val dog2 = Dog()
    val dog3 = Dog()

    queue.add(cat1)
    queue.add(dog1)
    queue.add(cat2)
    queue.add(dog2)
    queue.add(cat3)
    queue.add(dog3)

    println("pollCat -> ${queue.pollCat().type}")
    println("pollDog -> ${queue.pollDog().type}")

    println("------pollAll------")
    val petList = queue.pollAll()
    petList.forEach {
        println(it.type)
    }
}