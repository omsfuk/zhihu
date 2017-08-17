package cn.omsfuk.zhihu.config;

import cn.omsfuk.zhihu.dao.AnswerDAO;
import cn.omsfuk.zhihu.dao.QuestionDAO;
import cn.omsfuk.zhihu.dao.UserDAO;
import cn.omsfuk.zhihu.entity.Answer;
import cn.omsfuk.zhihu.entity.Question;
import cn.omsfuk.zhihu.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/16
 */

@Service
public class DefaultConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserDAO userDAO = applicationContext.getBean(UserDAO.class);
        User user = new User();
        user.setName("omsfuk");
        user.setGender(1);
        user.setJoinTime(new Date(System.currentTimeMillis()));
        user.setPassword("admin");
        user.setSignature("一本正经的逗比");
        userDAO.save(user);



        QuestionDAO questionDAO = applicationContext.getBean(QuestionDAO.class);
        Question question = new Question();
        question.setTitle("讲故事是怎样的一种体验");
        question.setQuestioner(user);
        question.setAnswerCount(1);
        questionDAO.save(question);

        Question question2 = new Question();
        question2.setTitle("大家见到抄袭 MC 最猖狂的游戏是什么？");
        question2.setDesc("有一次，班里开元旦晚会允许带手机。有个女生拿着一个手机在那玩《迷你世界》。我说：“这个游戏是抄袭MC的，你最好别玩了。”结果那个女生说：“我都看过了，那个什么我的世界是抄袭迷你世界的。你看它名字、玩法甚至外观都是一样的。”我立马就冒火了：“你不要说胡话了好不好！我在这里跟你解释不清楚，但迷你世界的确是抄袭的。你趁早删了吧！”然后那个女生就开始爆粗口：“TMD什么MC不MC的！我假如玩的盗版那你玩的就是盗版中的盗版！XXX我告诉你！MC这个游戏根本就不存在！应用商店里的你玩的我玩的大家玩的都是TND盗版！”然后和我一样是MC迷的同位也说：“她说的没错，我们确实是玩的盗版。正版要花大钱买的。”然后也聚到看迷你世界的人群中了(`Д´)父老乡亲给我评评理啊！");
        question2.setQuestioner(user);
        question2.setAnswerCount(1);
        questionDAO.save(question2);



        AnswerDAO answerDAO = applicationContext.getBean(AnswerDAO.class);
        Answer answer = new Answer();
        answer.setAnswerer(user);
        answer.setText("先怼一波“迷你”之流。我认为，单从“可玩性”来看，Minecraft就甩“迷你”九条街了。完善的游戏体验，多样的服务器、地图和资源包都成为Minecraft胜利的理由。 可扩展性，迷你世界是*完全做不到*的。 为什么？虽然两种游戏均为闭源，但是由于可玩性的引诱和年代的久远，再加上国外大规模的开发者社区和积极的开源精神，使得Minecraft有了许多大规模的插件平台，例如最出名的Forge，服务器的Bukkit，也包含官方的便携版插件。而迷你世界由于面向低龄市场，*不存在*插件之类的拓展工具，导致整个游戏玩法僵死。就算以后有出新玩法，大多都是从Forge Mod借(Chao)鉴(Xi)而来。玩“迷你”的人，大多都是饮水不思源的。例如一个孩子在“迷你”有一把哒哒哒冒蓝火的枪就洋洋自得，他不知道这来源于Flan Mod等等插件。<br/> 觉得Minecraft像素太低材质难看，你尽可装OptifFine，然后去下载材质包(我用的是原版高清32x，Minecraft CurseForge材质包区排名第一)。 觉得一个人太无聊，你尽可去MCBBS找服务器，甚至可以去玩网易国服Hypixel。现在公测不限号不删档，你尽可玩个够。 人物材质难看？去MCBBS皮肤版去下，根据教程去替换，培养能力，多好。 还有问题？STFW&RTFM。<br/> Minecraft并非万能，但它终究是沙盒类游戏资质最老，好评最多的。 尽管如此，你仍可去玩“迷你”之流，享受国内一些游戏产商的“优质服务”，没有任何人会阻止你。 毕竟，国外的优秀作品在国内是没有人权的啊。。。 (我的原回答 https://www.zhihu.com/question/39610303/answer/213548750)最后，狠狠地怼一波下面道德绑架之流。国外的优秀作品在国内没有人权，就容许你们随意地篡改？？？？？");
        answer.setCreateDate(new Date(System.currentTimeMillis()));
        answer.setQuestion(question2);
        answerDAO.save(answer);

        Answer answer1 = new Answer();
        answer1.setAnswerer(user);
        answer1.setText("从前有座山，山里有个庙，庙里有个老和尚<br/><br/>从前有座山，山里有个庙，庙里有个老和尚<br/><br/>从前有座山，山里有个庙，庙里有个老和尚<br/><br/>");
        answer1.setCreateDate(new Date(System.currentTimeMillis()));
        answer1.setQuestion(question);
        answerDAO.save(answer1);
    }
}
