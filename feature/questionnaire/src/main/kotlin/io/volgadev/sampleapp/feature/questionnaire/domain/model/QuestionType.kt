package io.volgadev.sampleapp.feature.questionnaire.domain.model

/**
 * Тип вопроса
 */
internal sealed class QuestionType(
    open val id: String, open val questionText: String
) {

    /**
     * Вопрос с вариантами ответа
     * @property answers ответы
     * @property суммарное количество ответов
     */
    data class Quiz(
        override val id: String,
        override val questionText: String,
        val answers: List<QuizAnswer>
    ) : QuestionType(id, questionText) {
        val allVotesCount = answers.sumOf { it.votesCount }
    }

    /**
     * Сопоставление элементов левого столба с правым
     * @property leftItems левые значения
     * @property rightItems правые значения
     */
    data class Matching(
        override val id: String,
        override val questionText: String,
        val leftItems: List<String>,
        val rightItems: List<String>
    ) : QuestionType(id, questionText)

    /**
     * Подстановка пропусков
     * @property textWithPlaceholders текст с плейсхолдерами [placeholder]
     * @property placeholderItems варианты для подстановки
     */
    data class GapSubstitution(
        override val id: String,
        override val questionText: String,
        val textWithPlaceholders: String,
        val placeholder: String = "[]",
        val placeholderItems: List<String>
    ) : QuestionType(id, questionText)

    /**
     * Рейтинг
     * @property maxValue максимальное значение рейтига
     */
    data class Rating(
        override val id: String, override val questionText: String, val maxValue: Int = 5
    ) : QuestionType(id, questionText)
}