# Kofix

A Kotlin library to create instances of classes filled with random values. Think [Autofixture](https://github.com/AutoFixture/AutoFixture), but for Kotlin.

Use it for whatever, but the whole reason for the existence of this library is to minimize the arrange/setup phase of your unit tests without requiring changes to your production code.

## Usage

If you just want a fixture and don’t care about any values, simply call the `fake` function

```kotlin
import faker.fake

data class Person(val name: String, val age: UInt)

val tenPeople = fake<Person>().take(10).toList()
```

If you want to restrict values to a more sensible value in your domain, you can override them with a custom value generator. Let’s restrict `age` to between 0 and 120:

```kotlin
import faker.fake
import faker.generator

data class Person(val name: String, val age: UInt)

val tenPeople = fake<Person>()
    .set(Person::age, Int::generator(0, 120))
    .take(10)
    .toList()
```

### Caveats and limitations

- Custom classes are instantiated through their **primary constructor**, and only that. This is somewhat of a coincidence, but also makes sense. It does however mean that var properties not set in the constructor won’t be touched.
- Restricting/specifying values is done with the `Class::parameter` syntax. Because of this, constructor params that are **not** vars can’t be restricted. They will still receive random values based on their type.
- There is no peeking at parameter names or such to guess the semantic content. This will probably not happen either.

## A note about Java

So, this library is made with Kotlin in mind. Will it work with Java? I don’t know, to be honest. I haven’t written Java in ~5 years.
If you have any clue, and want to improve the Java API without compromising the Kotlin API, give me a shout!