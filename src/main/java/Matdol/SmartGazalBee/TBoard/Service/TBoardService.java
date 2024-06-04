package Matdol.SmartGazalBee.TBoard.Service;

import Matdol.SmartGazalBee.TBoard.Controller.TBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;

public interface TBoardService {
    public TBoardDTO create(TBoardDTO tBoardDTO);

    public TBoardDTO update(TBoardDTO tBoardDTO, Long postId);

    public void delete(Long postId);

    public Page<TBoardDTO> findAll(Pageable pageable);

    public Page<TBoardDTO> findMyPosts(Pageable pageable, Long purchaserId);

    public Page<TBoardDTO> findPopularPosts(Pageable pageable, Long standard);
    public Page<TBoardDTO> findRecentPosts(Pageable pageable);

        }
