//package services;
//
//import data.models.Wallet;
//public class Login {
//    private Wallet currentWallet;
//    private final WalletServices walletService = new EWalletWalletServices();
//
//    private Login(){
//
//    }
//
//    public void login (String userName, String passWord){
//       currentWallet = walletService.findWalletByUserNameAndPassWord(userName,passWord);
//
//    }
//    public static Login login(){
//        return new Login();
//    }
//
//    public boolean isLoggedIn(){
//        return currentWallet != null;
//    }
//    public int getCurrentWalletId(){
//        if(isLoggedIn()) return currentWallet.getWalletId();
//        return 0;
//    }
//    public Wallet getCurrentWallet(){
//        return currentWallet;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Login{");
//        sb.append("currentWallet=").append(currentWallet);
//        sb.append('}');
//        return sb.toString();
//    }
//}
////    public static Wallet login(String userName, String passWord){
////      currentWallet = walletService.findWalletByUserNameAndPassWord(userName,passWord);
////       return currentWallet;
////    }
////    public static void logOut(){
////        currentWallet = null;
////    }
////    public static boolean isLoggedIn(){
////        return currentWallet !=null;
////    }
////    public static int getCurrentWalletId(){
////        if(isLoggedIn()) return currentWallet.getWalletId();
////        return 0;
////    }
