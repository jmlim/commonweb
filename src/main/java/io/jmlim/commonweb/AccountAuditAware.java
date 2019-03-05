package io.jmlim.commonweb;

import io.jmlim.commonweb.post.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAuditAware implements AuditorAware<Account> {

    /***
     * 여기선 씨큐리티 설정까진 하지 않고 실행이 되는지만 확인.
     * @return
     */
    @Override
    public Optional<Account> getCurrentAuditor() {
        // 여기다 유저 꺼내오는 정보만 넣어주면 됨.
        System.out.println("looking for user");
        return Optional.empty();
    }
}
