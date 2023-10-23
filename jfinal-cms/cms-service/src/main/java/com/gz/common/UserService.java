package com.gz.common;

import com.gz.common.model.Role;
import com.gz.common.model.User;
import com.gz.utils.StringUtil;
import com.jfinal.plugin.activerecord.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/2.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public List<User> getList(){
        return userRepository.findByStatusNot("administrator");
    }
    public Page<User> getPage(int pageNum, int pageSize,String role){
        return userRepository.findByRoleId(roleRepository.findByRole(role).getId(), PageRequest.of(pageNum, pageSize));
    }
    public User getUserByAccount(String account){
        return userRepository.findByUsernameOrMobileOrEmail(account, account, account);
    }
    public User getUserByUserName(String account){
        return userRepository.findByUsername(account);
    }
    public User getUserByUserEmail(String account){
        return userRepository.findByEmail(account);
    }
    public User getUserByUserMobile(String account){
        return userRepository.findByMobile(account);
    }
    public Map<String,Object> updateUser(User user){
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        if(user==null){
            data.put("msg","user为空");
        }else if(user.getId()!=null&&user.getId()!=0&&user.update()){
            data.put("code",0);
            data.put("msg","用户信息更新成功");
        }else{

            if(!StringUtil.isEmpty(user.getEmail())&&getUserByUserEmail(user.getEmail())!=null){
                data.put("msg","邮箱已注册");
            }else if(!StringUtil.isEmpty(user.getMobile())&&getUserByUserMobile(user.getMobile())!=null){
                data.put("msg","手机号已注册");
            }else if(StringUtil.isEmpty(user.getUsername())){
                data.put("msg","用户名不能为空");
            }else if(getUserByUserName(user.getUsername())!=null){
                data.put("msg","用户名已存在");
            }else if(StringUtil.isEmpty(user.getPassword())){
                data.put("msg","用户名密码为空");
            }else if(userRepository.save(user) != null){
                data.put("code",0);
                data.put("msg","用户信息创建成功");
            }else{
                data.put("msg","用户信息创建失败");
            }

        }

        return data;
    }
    public User getUserByToken(String token){
        return userRepository.findByToken(token);
    };
    public Role getRole(int userId){
        return roleRepository.findById(userRepository.findById(userId).getRoleId()).orElse(null);
    }
    public List<User> getListByRole(String role){
        return userRepository.findByRoleId(roleRepository.findByRole(role).getId());
    }
}
