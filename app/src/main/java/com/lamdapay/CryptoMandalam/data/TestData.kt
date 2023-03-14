package com.lamdapay.CryptoMandalam.data

import com.lamdapay.CryptoMandalam.domain.model.*


class TestData {

    fun getCrowdFundsList(): List<FundModel> {
        return listOf(
            FundModel(
                name = "My Awesome Crowdfunding Project",
                description = "Help me fund my awesome project",
                imageUrl = "https://my-awesome-project.com/image.jpg",
                pledgedAmount = 100.0,
                fundingGoal = 200.0,
                progress = 0.5f,
                daoName = "funding_dao#1",
                crowdfundingTiming = "23th Feb",
                fundCategory = "climate change",
            ),
            FundModel(
                name = "Second Awesome Crowdfunding Project",
                description = "Help me fund my awesome project",
                imageUrl = "https://my-awesome-project.com/image.jpg",
                pledgedAmount = 1000.0,
                fundingGoal = 5000.0,
                progress = 0.2f,
                daoName = "funding_dao#2",
                crowdfundingTiming = "21th Feb",
                fundCategory = "tech",
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
            ),
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
            )
        )
    }

    fun getListOfProposal(): List<Proposal>{
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
            proposal1,
            proposal2
        )
    }

}