package ru.vyaacheslav.suhov.imeit.gateway

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import io.reactivex.schedulers.Schedulers
import io.reactivex.plugins.RxJavaPlugins
import ru.vyaacheslav.suhov.imeit.entity.AuthData
import ru.vyaacheslav.suhov.imeit.entity.User
import ru.vyaacheslav.suhov.imeit.interactor.AccountInteractor

// Т.к. в Kotlin все final использовал библиотку MockK - https://mockk.io

class AccountInteractorImplTest {

    // Пытался протестировать Firebase в итоге вообще не нашел инфы
    // В документации Firebase по андроиду про тесты ничего, единственная вкладка про тестирование предлагает это:
    // https://firebase.google.com/docs/functions/unit-testing но это уже более высокий уровень.

    companion object {
        private const val VALID_UID = "uid"
        private const val VALID_EMAIL = "test@test.test"
        private const val VALID_PASS = "123456"
    }

    @MockK
    private lateinit var interactor: AccountInteractor
    private var authData: AuthData? = null

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        authData = AuthData(VALID_EMAIL, VALID_PASS, User())
    }

    @Test
    fun testSignIn() {
        // given:
        every { interactor.signIn(VALID_EMAIL, VALID_PASS) } returns Single.just(false)

        // when:
        val test = interactor.signIn(VALID_EMAIL, VALID_PASS).test()

        test.assertComplete()
        test.assertNoErrors()

        // then:
        verify { interactor.signIn(VALID_EMAIL, VALID_PASS) }
    }

    @Test
    fun createAccountEmail() {

        // given:
        every { interactor.createAccountEmail(authData!!) } returns Single.just(true)

        // when:
        val test = interactor.createAccountEmail(authData!!).test()
        test.assertComplete()
        test.assertNoErrors()

        // then:
        verify { interactor.createAccountEmail(authData!!) }
    }

    @Test
    fun getAccount() {

        // given:
        every { interactor.getAccount(VALID_UID) } returns Observable.just( User())

        // when:
        interactor.getAccount(VALID_UID)

        // then:
        verify { interactor.getAccount(VALID_UID) }

    }

    @Test
    fun isSigned() {

        // given:
        every { interactor.isSigned() } returns Single.just(true)

        // when:
        interactor.isSigned()

        // then:
        verify { interactor.signOut() }
    }

    @Test
    fun signOut() {
        // given:
        every { interactor.signOut() } returns Single.just(true)

        // when:
        interactor.signOut()

        // then:
        verify { interactor.signOut() }
    }
}