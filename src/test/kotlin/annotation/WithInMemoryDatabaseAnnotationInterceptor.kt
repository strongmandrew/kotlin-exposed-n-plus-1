package annotation

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext
import java.lang.reflect.Constructor

class WithInMemoryDatabaseAnnotationInterceptor : InvocationInterceptor {

    private val inMemoryDatabaseBootstrapper = InMemoryDatabaseBootstrapper()

    /**
     * Перехватываем создание тестового класса для инициализации встроенной БД
     */
    override fun <T : Any?> interceptTestClassConstructor(
        invocation: InvocationInterceptor.Invocation<T>?,
        invocationContext: ReflectiveInvocationContext<Constructor<T>>?,
        extensionContext: ExtensionContext?,
    ): T {
        invocationContext?.run {
            if (targetClass.isAnnotationPresent(WithInMemoryDatabase::class.java)) {
                inMemoryDatabaseBootstrapper.bootstrap()
            }
        }
        return super.interceptTestClassConstructor(invocation, invocationContext, extensionContext)
    }
}