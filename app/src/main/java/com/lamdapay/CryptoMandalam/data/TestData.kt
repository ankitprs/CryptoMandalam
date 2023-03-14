package com.lamdapay.CryptoMandalam.data

import com.lamdapay.CryptoMandalam.domain.model.*


class TestData {

    fun getCrowdFundsList(): List<FundModel> {

        return listOf(
            FundModel(
                "South Sudan",
                "In 2023, humanitarians required $1.7 billion to reach 6.8 million people in South Sudan. People across the country continue to endure conflict, violence and weather shocks, including intense flooding. Protection concerns are increasing, and an estimated 2.8 million people, especially women and girls, are at risk of gender-based violence.",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.45.32%20AM.jpeg?alt=media&token=a4227fc5-9321-4020-be81-9e1f55b2eacb",
                1045.45,
                5045.45,
                20.45,
                "UNESCO",
                "15 March 2023",
                "Humanitarian",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.40.00%20AM.jpeg?alt=media&token=88670d94-b8d4-4065-a322-2cbf8aa82356",
                "0xjklgsfbvfbfbegr4434f"
            ),


            FundModel(
                "Climate Change",
                "Climate change is one of the most pressing issues facing our planet today, and it is of critical importance for several reasons: Impact on ecosystems and biodiversity, Public health, Economic costs, Social and political instability, Global responsibility, etc.",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.40.02%20AM.jpeg?alt=media&token=dab65bb7-404a-4f6b-aa34-abc84924d1b1",
                1045.45,
                5045.45,
                20.45,
                "UNESCO",
                "10 March 2023",
                "Humanitarian",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.40.00%20AM.jpeg?alt=media&token=88670d94-b8d4-4065-a322-2cbf8aa82356",
                "0xjklgskasbdvjhweri2f"
            ),
            FundModel(
                "Education for All",
                "Education transforms lives and is at the heart of UNESCO’s mission to build peace, eradicate poverty and drive sustainable development. It is a human right for all throughout life. UNESCO provides global and regional leadership in education, strengthens education systems worldwide and responds to contemporary global challenges through education with gender equality as an underlying principle. Its work encompasses quality educational development from pre-school to higher education and beyond.",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.43.04%20AM.jpeg?alt=media&token=9efb9c76-6bce-466b-830d-16f9eae67c0a",
                1045.45,
                5045.45,
                20.45,
                "UNESCO",
                "2 March 2023",
                "Humanitarian",
                "https://firebasestorage.googleapis.com/v0/b/cryptomandalam.appspot.com/o/WhatsApp%20Image%202023-03-15%20at%2012.40.00%20AM.jpeg?alt=media&token=88670d94-b8d4-4065-a322-2cbf8aa82356",
                "0xernbjeni323grjklgsf"
            )
        )
    }

    fun getListOfDaoOfUser(): List<DaoModel> {
        return listOf(
            DaoModel(
                "0x4254gwgr5ythth",
                "SuperTeam",
                "content create dao for working dao",
                "https://superteam.fun/_next/image?url=https%3A%2F%2Fsuper-static-assets.s3.amazonaws.com%2F75e99297-73de-4946-ba6b-0ac603638793%2Fimages%2F9ee6c5d5-58a1-4289-8252-e97878daa28c.png&w=1200&q=80",
                UserModel("igfagth", "user ankit", "0xgojnajronr"),
                emptyList(),
                emptyList(),
                "SPD",
                "0xfgfrgreheraht",
                08923.4
            ), DaoModel(
                "0x4254gwgr5ythth",
                "SuperTeam",
                "content create dao for working dao",
                "https://superteam.fun/_next/image?url=https%3A%2F%2Fsuper-static-assets.s3.amazonaws.com%2F75e99297-73de-4946-ba6b-0ac603638793%2Fimages%2F9ee6c5d5-58a1-4289-8252-e97878daa28c.png&w=1200&q=80",
                UserModel("igfagth", "user ankit", "0xgojnajronr"),
                emptyList(),
                emptyList(),
                "SPD",
                "0xfgfrgreheraht",
                08923.4
            )
        )
    }

    fun getListOfProposal(): List<Proposal> {
        val user1 = UserModel("1", "John", "0x123456789")
        val user2 = UserModel("2", "Jane", "0x987654321")
        val user3 = UserModel("3", "Bob", "0x789456123")
        val user4 = UserModel("4", "Alice", "0x456123789")
        val vote1 = Vote(user1, true)
        val vote2 = Vote(user2, false)
        val vote3 = Vote(user3, true)
        val proposal1 = Proposal(
            1,
            "Project X",
            "A project to develop a new product",
            user1,
            listOf(vote1, vote2, vote3),
            10000.0,
            5000.0,
            true
        )

        val proposal2 = Proposal(
            2,
            "Project Y",
            "A project to build a new website",
            user2,
            listOf(vote1, vote3),
            5000.0,
            2500.0,
            false
        )
        return listOf(
            proposal1, proposal2
        )
    }

}