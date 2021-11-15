package Testing;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.UUID;
    
    import org.junit.Test;
    
    import Model.*;


public class UserListTester {

    UserList userList;

    ArrayList<User> users = new ArrayList<>(Arrays.asList(
        userList.createUser("employer","test","test@email.com","testUser1",1),
        userList.createUser("student","test2","test2@email.com","testUser2",0)));
    
        @Test
        public void testGetInstance() {
            UserList.getInstance().setUserList(users);
            assertEquals(users, UserList.getInstance().getUsers());
        } 

    @Test
    public void testGetUserByIdIfExistsWithId() {
        UserList.getInstance().setUserList(users);
        User internship = users.get(0);
        assertEquals(internship, UserList.getInstance().getUserById(internship.getId()));
    }

    @Test
    public void testGetUserByIdIfNotExistsWithId() {
        UserList.getInstance().setUserList(users);
        assertEquals(null, UserList.getInstance().getUserById(UUID.randomUUID()));
    }

    @Test
    public void testGetUserByIdIfListEmpty() {
        UserList.getInstance().setUserList(new ArrayList<>());
        assertEquals(null, UserList.getInstance().getUserById(UUID.randomUUID()));
    }

    @Test
    public void testGetUserByIdIfIdNull() {
        UserList.getInstance().setUserList(users);
        assertEquals(null, UserList.getInstance().getUserById(null));
    }

    @Test
    public void testRemoveByIdIfIdIsInList() {
        ArrayList<User> usersTwo = (ArrayList<User>) users.clone();
        User notToContain = usersTwo.get(0);
        UserList.getInstance().setUserList(usersTwo);
        UserList.getInstance().removeUserById(notToContain.getId());
        assertTrue(!UserList.getInstance().getUsers().contains(notToContain));
    }

    @Test
    public void testRemoveByIdIfIdIsNotInList() {
        UserList.getInstance().setUserList(users);
        UserList.getInstance().removeUserById(UUID.randomUUID());
        assertEquals(users, UserList.getInstance().getUsers());
    }

    @Test
    public void testRemoveByIdIfIdIsNull() {
        UserList.getInstance().setUserList(users);
        UserList.getInstance().removeUserById(null);
        assertEquals(users, UserList.getInstance().getUsers());
    }

    @Test
    public void addUser() {
        UserList.getInstance().setUserList(new ArrayList<>());
        User toAdd = userList.createUser("firstName","lastName","email@portia.com","strongPassword",0);
        UserList.getInstance().addUser(toAdd);
        assertTrue(UserList.getInstance().getUsers().contains(toAdd));
    }

    @Test
    public void testAddUserIfUserIsNull(){
        UserList.getInstance().setUserList(users);
        UserList.getInstance().addUser(null);
        assertEquals(users.get(users.size()-1), UserList.getInstance().getUsers().get(UserList.getInstance().getUsers().size()-1));
    }

}