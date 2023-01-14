package io.volgadev.sampleapp.feature.questionnaire.data

import io.volgadev.sampleapp.feature.questionnaire.domain.QuestionnaireDemoRepository
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuestionType
import io.volgadev.sampleapp.feature.questionnaire.domain.model.QuizAnswer

internal class QuestionnaireDemoRepositoryImpl : QuestionnaireDemoRepository {

    override fun getQuizItems(): List<QuestionType.Quiz> {
        return listOf(
            QuestionType.Quiz(
                id = "1",
                questionText = "В каком году был ЧМ по футболу в России?",
                answers = listOf(
                    QuizAnswer("1", "2008", 1),
                    QuizAnswer("2", "2014", 2),
                    QuizAnswer("3", "2018", 3, isCorrect = true),
                    QuizAnswer("4", "Такого не было", 0),
                )
            ),
            QuestionType.Quiz(
                id = "1",
                questionText = "Кто стал чемпионом мира на ЧМ в России?",
                answers = listOf(
                    QuizAnswer("1", "Хорватия", 1),
                    QuizAnswer("2", "Франция", 2, isCorrect = true),
                    QuizAnswer("3", "Аргентина", 3),
                    QuizAnswer("4", "Россия", 0),
                )
            )
        )
    }

    override fun getPairMappingItems(): List<QuestionType.PairMapping> {
        return listOf(
            QuestionType.PairMapping(
                id = "1",
                questionText = "Найдите соответствие",
                itemsMap = mapOf(
                    "Аргентина" to "Месси",
                    "Португалия" to "Роналдо",
                    "Бразилия" to "Неймар",
                    "Россия " to "Акинфеев"
                )
            ),
            QuestionType.PairMapping(
                id = "2",
                questionText = "Найдите соответствие",
                itemsMap = mapOf(
                    "Аргентина" to "Буэнос-Айрос",
                    "Португалия" to "Лиссабон",
                    "Бразилия" to "Рио-Де-Жанейро",
                    "Россия " to "Москва"
                )
            )
        )
    }

    override fun getRatingItems(): List<QuestionType.Rating> {
        return listOf(
            QuestionType.Rating(
                id = "1",
                questionText = "Как вам этот пост про чемпионат мира?",
                maxValue = 5
            ),
            QuestionType.Rating(
                id = "2",
                questionText = "Насколько хотите еще?",
                maxValue = 5
            ),
            QuestionType.Rating(
                id = "3",
                questionText = "Двигаемся дальше?",
                maxValue = 5
            )
        )
    }

    override fun getGapFillingItems(): List<QuestionType.GapFilling> {
        return listOf(
            QuestionType.GapFilling(
                id = "1",
                questionText = "Заполните пропуски в тексте",
                textWithPlaceholders = "У лукоморья [] зеленый, злотая [] на дубе том. " +
                        "И днем и ночью [] ученый все ходит по [] кругом.",
                answers = listOf("дуб", "цепь", "кот", "цепи")
            ),
            QuestionType.GapFilling(
                id = "2",
                questionText = "Заполните пропуски в тексте",
                textWithPlaceholders = "Пойдет направо - [] заводит, налево - [] говорит." +
                        "Там чудеса, там [] бродит, [] на ветвях сидит.",
                answers = listOf("песнь", "сказку", "леший", "русалка")
            ),
        )
    }
}
