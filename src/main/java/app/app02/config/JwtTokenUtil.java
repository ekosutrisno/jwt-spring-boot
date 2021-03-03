package app.app02.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  public static final Long KADALUARSA = TimeUnit.SECONDS.toSeconds(10);

  private final String secret = "ekosutrisno"; // Secret Bebas mau apapun

  // mengambil usernama dari JWT token
  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  // mengambil tanggal kadaluarsa dari JWT token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  // mengambil claim dari JWT
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimToken) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimToken.apply(claims);
  }

  // mengambil beberpa informasi dari JWT --->[*butuh sekret key]
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  // Mengecek apakah token udah kadaluarsa atau belum
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  // generate token untuk dikirim ke user
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  // Saat creating the token:
  // 1. tentukan claims token, tanggal dibuat, kadaluarasnya kapan, username/user, dan ID
  // 2. ttd JWT dengan algoritma HS512 and secret key.
  // 3. kemudian compact jadi satu string

  // fungsi buat mengenerate token
  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  // validasi token
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}